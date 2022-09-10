package com.virta.nearbyservices.data

import com.virta.nearbyservices.data.model.StationListModel
import com.virta.nearbyservices.data.repository.login.LoginRepository
import com.virta.nearbyservices.data.repository.stations.StationsRepository
import javax.inject.Inject

class RepositoryManager @Inject constructor(
    private val loginRepository: LoginRepository,
    private val stationsRepository: StationsRepository
) : IRepositoryManager {
    override suspend fun login(username: String, password: String): Boolean {
        return loginRepository.login(username, password)
    }

    override suspend fun getStations(): List<StationListModel> {
        return stationsRepository.getStations()
    }
}