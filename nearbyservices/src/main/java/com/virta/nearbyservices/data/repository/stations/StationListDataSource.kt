package com.virta.nearbyservices.data.repository.stations

import android.content.Context
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.model.StationModel
import com.virta.nearbyservices.data.repository.BaseService
import com.virta.nearbyservices.data.utils.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Is it right to make this class singleton
@Singleton
class StationListDataSource @Inject constructor(
    @ApplicationContext val context: Context
) : BaseService() {
    private var stationListService: StationListService =
        RetrofitClient.CreateService.create(StationListService::class.java)
    private var encryptedPreferences: EncryptedPreferences =
        EncryptedPreferences.getInstance(context)
    private val count = AtomicInteger(0)

    suspend fun getStationList(params: Map<String, Double>): NetworkResult<List<StationModel>> {
        val currentLatitude = params.getValue(LATITUDE_KEY)
        val currentLongitude = params.getValue(LONGITUDE_KEY)

//        if (currentLatitude < LATITUDE_MIN_VALUE || currentLatitude > LATITUDE_MAX_VALUE
//            || currentLongitude < LONGITUDE_MIN_VALUE || currentLongitude > LONGITUDE_MAX_VALUE
//        ) {
//            return NetworkResult.Error(IOException("Coordinates not in range"))
//        }

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
        //        private const val DATA_KEY = "data"
        private const val LATITUDE_KEY = "latMax"
        private const val LONGITUDE_KEY = "longMax"
    }
}