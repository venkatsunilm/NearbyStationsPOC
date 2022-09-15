package com.virta.nearbyservices.domain

import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.repository.stations.StationDto

interface IRepositoryManager {

    suspend fun login(username: String, password: String): NetworkResult<Boolean>

    suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationDto>>

}