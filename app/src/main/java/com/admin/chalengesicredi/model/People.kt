package com.admin.chalengesicredi.model

import com.google.gson.annotations.SerializedName

data class People (

    @field:SerializedName("picture") val imgpeople: String?,
    @field:SerializedName("name") val peoplename: String?,
    @field:SerializedName("eventId") val ideventopeople: Int,
    @field:SerializedName("id") val idpeople: Int
)
