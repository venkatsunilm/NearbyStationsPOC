package com.virta.nearbyservices.data

import com.virta.nearbyservices.data.model.StationListModel

interface IRepositoryManager {

    suspend fun login(username: String, password: String): Boolean

    suspend fun getStations(): List<StationListModel>

}