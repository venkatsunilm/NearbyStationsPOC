package com.virta.nearbyservices.data.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.virta.nearbyservices.data.model.StationModel
import java.io.IOException
import java.text.DecimalFormat
import kotlin.math.*

object CoordinatesUtil {

    private const val earthMeanRadius = 6371e3 // meters
    private const val STATIONS_JSON_FILENAME = "stations.json"

    internal fun getSortedResultByDistance(
        currentLatitude: Double,
        currentLongitude: Double,
        stationsResponse: List<StationModel>,
        context: Context
    ): List<StationModel> {
        // Additional information: As the API service is not giving responses as part of known issue,
        // i am using mock data here
        return stationsResponse.ifEmpty {
            val parsedMockData = parseJsonToMockData(context)
            parsedMockData
                .map { stationListModel ->
                    stationListModel.distance = CoordinatesUtil.getStartEndPointsDistance(
                        currentLatitude,
                        currentLongitude,
                        stationListModel.latitude,
                        stationListModel.longitude
                    )
                }
            val sortedByDistance = parsedMockData.sortedBy { stationListModel
                ->
                stationListModel.distance
            }
            return sortedByDistance
        }
    }

    /**
    https://developer.android.com/reference/android/location/Location.html#distanceTo(android.location.Location)
    https://www.sisense.com/blog/latitude-longitude-distance-calculation-explained/
    https://www.movable-type.co.uk/scripts/latlong.html
    The below distance calculation is from the above references
    https://www.movable-type.co.uk/scripts/latlong-vincenty.html: Something more interesting is here, need to dig deeper

    const R = 6371e3; // metres
    const φ1 = lat1 * Math.PI/180; // φ, λ in radians
    const φ2 = lat2 * Math.PI/180;
    const Δφ = (lat2-lat1) * Math.PI/180;
    const Δλ = (lon2-lon1) * Math.PI/180; // val deltaLon = toLon - fromLon

    const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + // sin(deltaLat / 2).pow(2.0)
    Math.cos(φ1) * Math.cos(φ2) * // cos(fromLat) * cos(toLat)
    Math.sin(Δλ/2) * Math.sin(Δλ/2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    const d = R * c; // in metres
     **/
    private fun getStartEndPointsDistance(
        startLatitude: Double,
        startLongitude: Double,
        endLatitude: Double,
        endLongitude: Double
    ): Double {
        val startLatitudeToRadians = Math.toRadians(startLatitude)
        val endLatitudeToRadians = Math.toRadians(endLatitude)
        val deltaLatitude = Math.toRadians(endLatitude - startLatitude)
        val deltaLongitude = Math.toRadians(endLongitude - startLongitude)

        val step1 = (sin(deltaLatitude / 2).pow(2)
                + cos(startLatitudeToRadians) * cos(endLatitudeToRadians)
                * sin(deltaLongitude / 2).pow(2))

        val step2 = (2 * atan2(
            sqrt(step1),
            sqrt(1 - step1)
        ))

        val step3 = earthMeanRadius * step2 // in meters

        return DecimalFormat("#.##").format(step3).toDouble()
    }

    // TODO: Read the mock Json from raw folder
    private fun parseJsonToMockData(context: Context): List<StationModel> {
        val jsonString = getJsonDataFromAsset(context)
        val gson = Gson()
        val stationListType = object : TypeToken<List<StationModel>>() {}.type
        return gson.fromJson(jsonString, stationListType)
    }

    private fun getJsonDataFromAsset(context: Context): String {
        val jsonString: String
        try {
            jsonString =
                context.assets.open(STATIONS_JSON_FILENAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }
}
