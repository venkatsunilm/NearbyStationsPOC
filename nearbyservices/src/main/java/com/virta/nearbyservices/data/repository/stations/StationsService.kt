package com.virta.nearbyservices.data.repository.stations

import com.virta.nearbyservices.data.model.StationListModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface StationsService {
    @GET("stations")
    suspend fun getStations(
        @Header("Authorization") token: String,
        @QueryMap options: Map<String, Double>
    ): List<StationListModel>
}