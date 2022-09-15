package com.virta.nearbystations.viewmodel.stations

import androidx.lifecycle.ViewModel
import com.virta.nearbyservices.data.model.StationModel

class StationViewModel(item: StationModel) : ViewModel() {
    val locationName = item.name
    val distance = item.distance.toString()
    val address = item.city
}
