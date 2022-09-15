package com.virta.nearbyservices.domain

import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.repository.stations.StationDto
import com.virta.nearbyservices.domain.login.LoginRepository
import com.virta.nearbyservices.domain.stations.StationListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
// TODO: Remove this class and make these two functions as two use cases and keep it under DOMAIN module
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