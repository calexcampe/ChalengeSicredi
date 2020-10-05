package com.admin.chalengesicredi.model

import com.google.gson.annotations.SerializedName

data class DetailEvent (
    @field:SerializedName("date") val date: Long,
    @field:SerializedName("description") val descricao: String,
    @field:SerializedName("image") val imgevent: String,
    @field:SerializedName("longitude") val longitude: String?,
    @field:SerializedName("latitude") val latitude: String,
    @field:SerializedName("price") val preco: Double,
    @field:SerializedName("title") val titulo: String?,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("people") val owner: List<People>
)


