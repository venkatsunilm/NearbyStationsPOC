package com.virta.nearbystations.ui.stations

import com.virta.nearbyservices.data.model.StationModel

data class StationListResultStatus(
    val stationList: List<StationModel> = listOf(),
    val success: Boolean = false,
    val errorMessage: String? = null
)