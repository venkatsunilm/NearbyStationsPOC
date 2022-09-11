package com.virta.nearbystations.ui.stations

import androidx.lifecycle.ViewModel
import com.virta.nearbyservices.data.model.StationListModel

class StationViewModel(item: StationListModel) : ViewModel() {
    val locationName = item.name
    val distance = item.latitude.toString()
    val address = item.city
    val evses = item.electricVehicleConnectors
}
