package com.admin.chalengesicredi.model

import java.lang.Exception

sealed class DetailEventResult {

    data class Success(val data: DetailEvent, val datap: List<People>) : DetailEventResult()
    data class Error(val error: Exception) : DetailEventResult()
}