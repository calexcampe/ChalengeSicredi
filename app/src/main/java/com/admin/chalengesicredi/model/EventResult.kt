package com.admin.chalengesicredi.model

import java.lang.Exception

sealed class EventResult {

    data class Success(val data: List<Event>) : EventResult()
    data class Error(val error: Exception) : EventResult()
}