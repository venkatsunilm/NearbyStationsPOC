package com.virta.nearbyservices.data.model

import com.google.gson.annotations.SerializedName

data class StationModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("evses")
    val electricVehicleConnectors: List<ElectricVehicleConnectors>,
    var distance: Double
) {
    data class ElectricVehicleConnectors(
        @SerializedName("id")
        val id: Int,
        @SerializedName("connectors")
        val connectors: List<Connector>,
        @SerializedName("groupName")
        val groupName: String
    ) {
        data class Connector(
            @SerializedName("type")
            val type: String,
            @SerializedName("maxKw")
            val maxKw: Int
        )
    }
}


