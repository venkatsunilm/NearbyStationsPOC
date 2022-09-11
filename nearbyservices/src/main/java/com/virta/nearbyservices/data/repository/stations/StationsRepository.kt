package com.virta.nearbyservices.data.repository.stations

import com.virta.nearbyservices.data.model.StationListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsRepository @Inject constructor(private val stationsDataSource: StationsDataSource) {
    suspend fun getStations(params: Map<String, Double>): List<StationListModel> {
        return stationsDataSource.getStations(params)
    }
}