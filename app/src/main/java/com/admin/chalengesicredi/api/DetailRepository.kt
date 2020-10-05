

package com.admin.chalengesicredi.api

import android.util.Log
import com.admin.chalengesicredi.model.CheckinResult
import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.DetailEventResult
import com.admin.chalengesicredi.model.dto.CheckinDto
import com.admin.chalengesicredi.model.dto.CheckinResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

@ExperimentalCoroutinesApi
 class DetailRepository(private val service: ApiService) {

    private val searchResults = ConflatedBroadcastChannel<DetailEventResult>()
    private val postResults = ConflatedBroadcastChannel<CheckinResult>()
    private var lastRequestedPage = STARTING_PAGE_INDEX
    private var isRequestInProgress = false
    private val inMemoryCache = mutableListOf<DetailEvent>()


    suspend fun getSearchResultStream(query: String): Flow<DetailEventResult> {
        Log.d("DetailRepository", "New query: $query")
        inMemoryCache.clear()
        lastRequestedPage = 1
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
            val response = service.getDetail(query)
            Log.d("DetailRepository", "response $response")
            val repos = response
            inMemoryCache.addAll(listOf(repos))
            searchResults.offer(DetailEventResult.Success(repos,repos.owner))
            successful = true
        } catch (exception: IOException) {
            searchResults.offer(DetailEventResult.Error(exception))
        } catch (exception: HttpException) {
            searchResults.offer(DetailEventResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    suspend fun requestCheckin(query: CheckinDto): Flow<CheckinResult> {
        isRequestInProgress = true
        var response: CheckinResponse
        try {
            response = service.postcheckin(query)
            postResults.offer(CheckinResult.Success(response))
            Log.d("checkin", "response $response")
            val repos = response
        } catch (exception: IOException) {
            postResults.offer(CheckinResult.Error(exception))
        } catch (exception: HttpException) {
            postResults.offer(CheckinResult.Error(exception))
        }
        isRequestInProgress = false
        return postResults.asFlow()
    }

}
