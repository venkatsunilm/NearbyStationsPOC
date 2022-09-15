package com.virta.nearbyservices.data

import com.virta.nearbyservices.data.model.StationDto
import com.virta.nearbyservices.data.repository.login.LoginRepository
import com.virta.nearbyservices.data.repository.stations.StationListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryManager @Inject constructor(
    private val loginRepository: LoginRepository,
    private val stationListRepository: StationListRepository
) : IRepositoryManager {
    fun isUserAuthenticated() = loginRepository.isLoggedIn

    override suspend fun login(username: String, password: String): NetworkResult<Boolean> {
        return loginRepository.login(username, password)
    }

    override suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationDto>> {
        return stationListRepository.getStationList(params)
    }

}