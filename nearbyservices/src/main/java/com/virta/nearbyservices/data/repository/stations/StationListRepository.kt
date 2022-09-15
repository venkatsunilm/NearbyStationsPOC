package com.virta.nearbyservices.data.repository.stations

import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.model.StationDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationListRepository @Inject constructor(private val stationListDataSource: StationListDataSource) {
    suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationDto>> {
        return stationListDataSource.getStationList(params = params)
    }
}