package com.virta.nearbystations.ui.stations

import com.virta.nearbyservices.data.repository.stations.StationDto

data class StationListResultStatus(
    val stationList: List<StationDto> = listOf(),
    val success: Boolean = false,
    val errorMessage: String? = null
)