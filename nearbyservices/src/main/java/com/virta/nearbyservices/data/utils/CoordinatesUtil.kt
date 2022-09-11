package com.virta.nearbyservices.data.utils

import android.util.Log
import kotlin.math.*

object CoordinatesUtil {

    private val TAG = "venkat"
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

    // https://www.sisense.com/blog/latitude-longitude-distance-calculation-explained/
    // https://www.movable-type.co.uk/scripts/latlong.html
//    const R = 6371e3; // metres
//    const φ1 = lat1 * Math.PI/180; // φ, λ in radians
//    const φ2 = lat2 * Math.PI/180;
//    const Δφ = (lat2-lat1) * Math.PI/180;
//    const Δλ = (lon2-lon1) * Math.PI/180; // val deltaLon = toLon - fromLon
//
//    const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + // sin(deltaLat / 2).pow(2.0)
//    Math.cos(φ1) * Math.cos(φ2) * // cos(fromLat) * cos(toLat)
//    Math.sin(Δλ/2) * Math.sin(Δλ/2);
//    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//
//    const d = R * c; // in metres

//    fun distance(fromLat: Double, fromLon: Double, toLat: Double, toLon: Double): Double {
//        val radius = 6378137.0 // approximate Earth radius, *in meters*
//        val deltaLat = toLat - fromLat
//        val deltaLon = toLon - fromLon
//        val angle = 2 * asin(
//            sqrt(
//                sin(deltaLat / 2).pow(2.0) +
//                        cos(fromLat) * cos(toLat) *
//                        sin(deltaLon / 2).pow(2.0)
//            )
//        )
//        Log.d(TAG, "fromLatLon: $fromLat, $fromLon")
//        Log.d(TAG, "toLatLon: $toLat, $toLon")
//        Log.d(TAG, (radius * angle).toString())
//        return radius * angle
//    }

    // https://developer.android.com/reference/android/location/Location.html#distanceTo(android.location.Location)
    // https://www.sisense.com/blog/latitude-longitude-distance-calculation-explained/
    // https://www.movable-type.co.uk/scripts/latlong.html
    // The below distance calculation is from the above references
    // Step 1:
    fun distanceOther(
        startLatitude: Double,
        startLongitude: Double,
        endLatitude: Double,
        endLongitude: Double
    ): Double {

        val earthMeanRadius = 6371e3 // meters
        val startLatitudeToRadians = startLatitude * Math.PI / 180
        val endLatitudeToRadians = endLatitude * Math.PI / 180
        val deltaLatitude = (endLatitude - startLatitude) * Math.PI / 180
        val deltaLongitude = (endLongitude - startLongitude) * Math.PI / 180

        val step1 = ((sin(deltaLatitude / 2) * sin(deltaLatitude / 2))
                + (cos(startLatitudeToRadians) * cos(endLatitudeToRadians)
                * sin(deltaLongitude / 2) * sin(deltaLongitude / 2)))

        val step2 = (2 * atan2(
            sqrt(step1),
            sqrt(1 - step1)
        ))

        val step3 = earthMeanRadius * step2 // in meters
         Log.d(TAG, (step3).toString())
        return  step3
//        val theta = startLongitude - endLongitude
//        var dist = (sin(deg2rad(startLatitude))
//                * sin(deg2rad(endLatitude))
//                + (cos(deg2rad(startLatitude))
//                * cos(deg2rad(endLatitude))
//                * cos(deg2rad(theta))))
//        dist = acos(dist)
//        dist = rad2deg(dist)
//        dist *= 60 * 1.1515
//        Log.d(TAG, (dist).toString())
//        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

}

