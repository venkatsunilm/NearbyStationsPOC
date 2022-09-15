package com.virta.nearbystations.viewmodel.stations

import androidx.lifecycle.ViewModel
import com.virta.nearbyservices.data.model.StationDto

class StationViewModel(item: StationDto) : ViewModel() {
    val locationName = item.name
    val distance = item.distance.toString()
    val address = item.city
}
