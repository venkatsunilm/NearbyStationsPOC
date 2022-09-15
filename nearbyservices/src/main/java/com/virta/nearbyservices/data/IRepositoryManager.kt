package com.virta.nearbyservices.data

import com.virta.nearbyservices.data.model.StationDto

interface IRepositoryManager {

    suspend fun login(username: String, password: String): NetworkResult<Boolean>

    suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationDto>>

}