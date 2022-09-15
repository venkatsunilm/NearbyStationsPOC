package com.virta.nearbyservices.data.model

import com.google.gson.annotations.SerializedName

data class UserCredentials(
    @SerializedName("email")
    val userName: String = "",
    @SerializedName("code")
    val password: String = ""
)