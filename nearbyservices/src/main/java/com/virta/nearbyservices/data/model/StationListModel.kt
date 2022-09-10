package com.virta.nearbyservices.data.model

data class StationListModel(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val provider: String,
    val city: String,
    val evses: List<Evse>
)

data class Evse(
    val id: Int,
    val connectors: List<Connector>,
    val groupName: String
)

data class Connector(
    val type: String,
    val maxKw: Int
)
