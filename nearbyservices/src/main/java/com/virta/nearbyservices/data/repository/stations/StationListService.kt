package com.virta.nearbyservices.data.repository.stations

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface StationListService {
    @GET("stations")
    suspend fun getStations(
        @Header("Authorization") token: String,
        @QueryMap options: Map<String, Double>
    ): Response<List<StationDto>>
}