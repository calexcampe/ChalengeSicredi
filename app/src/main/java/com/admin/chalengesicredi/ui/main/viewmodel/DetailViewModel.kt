package com.admin.chalengesicredi.ui.main.viewmodel

import androidx.lifecycle.*
import com.admin.chalengesicredi.api.ApiService
import com.admin.chalengesicredi.api.DetailRepository
import com.admin.chalengesicredi.api.EventsRepository
import com.admin.chalengesicredi.model.DetailEventResult
import com.admin.chalengesicredi.model.EventResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(service: ApiService) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val repository = DetailRepository(service)

    val repoResult: LiveData<DetailEventResult> = queryLiveData.switchMap { queryString ->
        liveData {
            val repos = repository.getSearchResultStream(queryString).asLiveData(Dispatchers.Main)
            emitSource(repos)
        }
    }

    fun searchRepo(id: String) {
        queryLiveData.postValue(id)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            val immutableQuery = queryLiveData.value
            if (immutableQuery != null) {
                viewModelScope.launch {
                    repository.requestMore(immutableQuery)
                }
            }
        }
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

}