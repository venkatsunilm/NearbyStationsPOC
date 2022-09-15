package com.virta.nearbyservices.domain.stations

import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.repository.stations.StationDto
import com.virta.nearbyservices.data.repository.stations.StationListDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationListRepository @Inject constructor(private val stationListDataSource: StationListDataSource) {
    suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationDto>> {
        return stationListDataSource.getStationList(params = params)
    }
}