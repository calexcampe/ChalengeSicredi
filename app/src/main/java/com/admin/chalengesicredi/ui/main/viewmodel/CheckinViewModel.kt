package com.admin.chalengesicredi.ui.main.viewmodel

import androidx.lifecycle.*
import com.admin.chalengesicredi.api.ApiService
import com.admin.chalengesicredi.api.DetailRepository
import com.admin.chalengesicredi.model.CheckinResult
import com.admin.chalengesicredi.model.DetailEventResult
import com.admin.chalengesicredi.model.dto.CheckinDto
import com.admin.chalengesicredi.model.dto.CheckinResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CheckinViewModel  @Inject constructor(service: ApiService) : ViewModel()  {

    private val queryLiveData = MutableLiveData<CheckinDto>()
    private val repository = DetailRepository(service)

    val repoResult: LiveData<CheckinResult> = queryLiveData.switchMap { queryString ->
        liveData {
            val repos = repository.requestCheckin(queryString).asLiveData(Dispatchers.Main)
            emitSource(repos)
        }
    }

    fun postCheckin(id: CheckinDto) {
        queryLiveData.postValue(id)
    }
}