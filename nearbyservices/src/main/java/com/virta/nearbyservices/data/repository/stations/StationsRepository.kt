package com.virta.nearbyservices.data.repository.stations

import com.virta.nearbyservices.data.model.StationListModel
import javax.inject.Inject

class StationsRepository @Inject constructor(private val stationsDataSource: StationsDataSource) {
    suspend fun getStations(): List<StationListModel> {
        return stationsDataSource.getStations()
    }
}