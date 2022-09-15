package com.virta.nearbyservices.domain.stations

data class StationModel(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val provider: String,
    val city: String,
    val electricVehicleConnectors: List<ElectricVehicleConnectors>,
    var distance: Double
) {
    data class ElectricVehicleConnectors(
        val id: Int,
        val connectors: List<Connector>,
        val groupName: String
    ) {
        data class Connector(
            val type: String,
            val maxKw: Int
        )
    }
}



