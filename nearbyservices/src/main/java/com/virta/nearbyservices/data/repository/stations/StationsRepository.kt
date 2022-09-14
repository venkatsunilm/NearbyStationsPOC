package com.virta.nearbyservices.data.repository.stations

import com.virta.nearbyservices.data.model.StationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsRepository @Inject constructor(private val stationsDataSource: StationsDataSource) {
    suspend fun getStations(params: Map<String, Double>): List<StationModel> {
        return stationsDataSource.getStations(params)
    }
}