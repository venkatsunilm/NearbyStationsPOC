package com.virta.nearbyservices.data.repository.stations

import com.virta.nearbyservices.data.model.StationListModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface StationsService {

    companion object{
        private const val ACCEPT = "accept"
        private const val AUTHORIZATION = "Authorization"
    }

    @GET("stations")
    suspend fun getStations(
        @Header(ACCEPT) accept: String = "application/json",
        @Header(AUTHORIZATION) token: String,
        @QueryMap options: Map<String, Long>
    ): List<StationListModel>
}