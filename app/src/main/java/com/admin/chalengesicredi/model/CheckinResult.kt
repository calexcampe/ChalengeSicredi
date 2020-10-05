package com.admin.chalengesicredi.model

import com.admin.chalengesicredi.model.dto.CheckinResponse
import java.lang.Exception

sealed class CheckinResult {

    data class Success(val data: CheckinResponse) : CheckinResult()
    data class Error(val error: Exception) : CheckinResult()
}

