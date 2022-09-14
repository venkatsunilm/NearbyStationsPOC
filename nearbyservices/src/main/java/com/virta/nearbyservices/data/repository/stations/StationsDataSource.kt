package com.virta.nearbyservices.data.repository.stations

import android.content.Context
import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.model.StationModel
import com.virta.nearbyservices.data.utils.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Is it right to make this class singleton
@Singleton
class StationsDataSource @Inject constructor(
    @ApplicationContext val context: Context
) {
    private var stationsDataSource: StationsService =
        RetrofitClient.CreateService.create(StationsService::class.java)
    private var encryptedPreferences: EncryptedPreferences =
        EncryptedPreferences.getInstance(context)
    private val count = AtomicInteger(0)

    suspend fun getStations(params: Map<String, Double>): List<StationModel> {
        try {

            val currentLatitude = params.getValue(LATITUDE_KEY)
            val currentLongitude = params.getValue(LONGITUDE_KEY)

            if (currentLatitude < LATITUDE_MIN_VALUE || currentLatitude > LATITUDE_MAX_VALUE
                || currentLongitude < LONGITUDE_MIN_VALUE || currentLongitude > LONGITUDE_MAX_VALUE
            ) {
                return listOf()
            }

            val token = encryptedPreferences.getString(EncryptedPreferences.Keys.TOKEN)
            val stationsResponse = stationsDataSource.getStations(
                token = token,
                options = params
            )

            return CoordinatesUtil.getSortedResultByDistance(
                currentLatitude,
                currentLongitude,
                stationsResponse,
                context
            )

        } catch (error: Throwable) {
            throw ResponseError("Error fetching events", error)
        }
    }

    companion object {
//        private const val DATA_KEY = "data"
        private const val LATITUDE_KEY = "latMax"
        private const val LONGITUDE_KEY = "longMax"
    }
}