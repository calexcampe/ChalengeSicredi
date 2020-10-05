
package com.admin.chalengesicredi.model

import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for a Github repo that holds all the information about a repository.
 * Objects of this type are received from the Github API, therefore all the fields are annotated
 * with the serialized name.
 * This class also defines the Room repos table, where the repo [id] is the primary key.
 */
data class Event(
    @field:SerializedName("date") val date: Long,
    @field:SerializedName("description") val descricao: String,
    @field:SerializedName("image") val imgevent: String,
    @field:SerializedName("longitude") val longitude: String?,
    @field:SerializedName("latitude") val latitude: String,
    @field:SerializedName("price") val preco: Double,
    @field:SerializedName("title") val titulo: String?,
    @field:SerializedName("id") val id: Int

)

