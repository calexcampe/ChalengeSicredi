
package com.admin.chalengesicredi.api
import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.Event
import com.admin.chalengesicredi.model.dto.CheckinDto
import com.admin.chalengesicredi.model.dto.CheckinResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val IN_QUALIFIER = "in:name,description"


interface ApiService {

    //eventos
    @GET("{events}")
    suspend fun getRepo(
        @Path("events") events: String
    ): List<Event>

    @GET("events/{id}")
    suspend fun getDetail(
        @Path("id") id: String
    ): DetailEvent


    @POST("checkin")
    suspend fun postcheckin(
        @Body checkinDto: CheckinDto
    ): CheckinResponse


}