package com.virta.nearbyservices.data.repository.stations

import android.content.Context
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.model.StationDto
import com.virta.nearbyservices.data.repository.BaseService
import com.virta.nearbyservices.data.utils.CoordinatesUtil
import com.virta.nearbyservices.data.utils.EncryptedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationListDataSource @Inject constructor(
    @ApplicationContext val context: Context
) : BaseService() {
    private var stationListService: StationListService =
        RetrofitClient.CreateService.create(StationListService::class.java)
    private var encryptedPreferences: EncryptedPreferences =
        EncryptedPreferences.getInstance(context)
    private val count = AtomicInteger(0)

    suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationDto>> {
        val currentLatitude = params.getValue(LATITUDE_KEY)
        val currentLongitude = params.getValue(LONGITUDE_KEY)

        val token = encryptedPreferences.getString(EncryptedPreferences.Keys.TOKEN)
        val stationsResponse = apiCall {
            stationListService.getStations(
                token = token,
                options = params
            )
        }

        return when (stationsResponse) {
            is NetworkResult.Success -> {
                NetworkResult.Success(
                    CoordinatesUtil.getSortedResultByDistance(
                        currentLatitude,
                        currentLongitude,
                        stationsResponse.data,
                        context
                    )
                )
            }
            is NetworkResult.Error -> {
                NetworkResult.Error(stationsResponse.exception)
            }

        }
    }

    companion object {
        private const val LATITUDE_KEY = "latMax"
        private const val LONGITUDE_KEY = "longMax"
    }
}