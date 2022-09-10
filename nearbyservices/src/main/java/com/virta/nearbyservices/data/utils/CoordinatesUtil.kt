package com.virta.nearbyservices.data.utils

import com.virta.nearbyservices.data.model.StationListModel
import kotlin.math.*

class CoordinatesUtil {

//    private fun sortStations(currentLatitude: Double, currentLongitude: Double): Comparator<StationListModel> {
//        fun compare(stationFirst: StationListModel, stationSecond: StationListModel): Int {
//            val latitudeFirst: Double = stationFirst.latitude
//            val longitudeFirst: Double = stationFirst.longitude
//            val latitudeSecond: Double = stationSecond.latitude
//            val longitudeSecond: Double = stationSecond.longitude
//            val distanceToPlace1 = distance(currentLatitude, currentLongitude, latitudeFirst, longitudeFirst)
//            val distanceToPlace2 = distance(currentLatitude, currentLongitude, latitudeSecond, longitudeSecond)
//            return (distanceToPlace1 - distanceToPlace2).toInt()
//        }
//    }

    private fun distance(fromLat: Double, fromLon: Double, toLat: Double, toLon: Double): Double {
        val radius = 6378137.0 // approximate Earth radius, *in meters*
        val deltaLat = toLat - fromLat
        val deltaLon = toLon - fromLon
        val angle = 2 * asin(
            sqrt(
                sin(deltaLat / 2).pow(2.0) +
                        cos(fromLat) * cos(toLat) *
                        sin(deltaLon / 2).pow(2.0)
            )
        )
        return radius * angle
    }

//    fun orderedByDistance(currentLatitude: Double, currentLongitude: Double){
////        Collection.sort()
//        sortStations(currentLatitude, currentLongitude)
//    }
}

