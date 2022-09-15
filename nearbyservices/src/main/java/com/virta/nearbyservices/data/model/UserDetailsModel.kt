package com.virta.nearbyservices.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailsModel(
    @SerializedName("email")
    val userName: String = "",
    @SerializedName("code")
    val password: String = ""
)