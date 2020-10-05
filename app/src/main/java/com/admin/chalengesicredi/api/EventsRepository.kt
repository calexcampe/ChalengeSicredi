

package com.admin.chalengesicredi.api

import android.util.Log
import com.admin.chalengesicredi.model.Event
import com.admin.chalengesicredi.model.EventResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

@ExperimentalCoroutinesApi
 class EventsRepository(private val service: ApiService) {

    // caso necessario mantenha a lista de todos os resultados recebidos
    private val inMemoryCache = mutableListOf<Event>()

    // manter canal de resultados. O canal nos permite transmitir atualizações para
    //o subscriber  terá os dados mais recentes
    private val searchResults = ConflatedBroadcastChannel<EventResult>()

    //mantenha a última página solicitada. Quando a solicitação é bem-sucedida,
    // incrementa o número da página.
    private var lastRequestedPage = STARTING_PAGE_INDEX

    // evite acionar várias solicitações ao mesmo tempo
    private var isRequestInProgress = false

    /**
     * Repositórios de pesquisa cujos nomes correspondam à consulta, expostos como um fluxo de dados que emitirá
        cada vez que obtemos mais dados da rede.
     */

    suspend fun getSearchResultStream(query: String): Flow<EventResult> {
        Log.d("EventRepository", "New query: $query")
        lastRequestedPage = 1
        inMemoryCache.clear()
        requestAndSaveData(query)

        return searchResults.asFlow()
    }

    suspend fun requestMore(query: String) {
        if (isRequestInProgress) return
        val successful = requestAndSaveData(query)
        if (successful) {
            lastRequestedPage++
        }
    }

    suspend fun requestAndSaveData(query: String): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = service.getRepo(query)
            Log.d("DetailRepository", "response $response")
            val repos = response
            inMemoryCache.addAll(repos)
            searchResults.offer(EventResult.Success(repos))
            successful = true
        } catch (exception: IOException) {
            searchResults.offer(EventResult.Error(exception))
        } catch (exception: HttpException) {
            searchResults.offer(EventResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }


}
