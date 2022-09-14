package com.virta.nearbyservices.data

import com.virta.nearbyservices.data.model.StationModel

interface IRepositoryManager {

    suspend fun login(username: String, password: String): Boolean

    suspend fun getStations(params: Map<String, Double>): List<StationModel>

}