package com.virta.nearbyservices.data.repository.stations

import android.content.Context
import android.nfc.Tag
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.model.StationListModel
import com.virta.nearbyservices.data.utils.EncryptedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsDataSource @Inject constructor(
    @ApplicationContext val context: Context
) {

    private var stationsDataSource: StationsService = RetrofitClient.retrofitAuth()
        .create(StationsService::class.java)
    private var encryptedPreferences: EncryptedPreferences =
        EncryptedPreferences.getInstance(context)

    suspend fun getStations(): List<StationListModel> {
        try {
            val token = encryptedPreferences.getString(EncryptedPreferences.Keys.TOKEN)
            val params = hashMapOf<String, Long>()
//            params["latMin"] = 4.398458
//            params["longMin"] = 14.398458
            val stationsResponse = stationsDataSource.getStations(
                token = token,
                options = params
            )
            if (stationsResponse.isEmpty()) {
                return parseJsonToMockData(context, STATIONS_JSON_FILENAME)
            } else {
                return stationsResponse
            }
        } catch (error: Throwable) {
            throw ResponseError("Error fetching events", error)
        }
    }

    private fun parseJsonToMockData(context: Context, fileName: String): List<StationListModel> {
        val jsonString = getJsonDataFromAsset(context, fileName)
        val gson = Gson()
        val stationListType = object : TypeToken<List<StationListModel>>() {}.type
        val stationList: List<StationListModel> = gson.fromJson(jsonString, stationListType)

        for (item in stationList) {
            Log.d(TAG, item.name)
            Log.d(TAG, item.evses.toString())
        }

        return stationList
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }

    companion object {
        private const val DATA_KEY = "data"
        private const val STATIONS_JSON_FILENAME = "stations.json"
        private const val TAG = "Venkat"
    }
}