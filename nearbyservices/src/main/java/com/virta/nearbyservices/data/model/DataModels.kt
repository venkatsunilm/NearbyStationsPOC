package com.virta.nearbyservices.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    // TODO: Remove the user ID and name if not needed
    val userId: String = java.util.UUID.randomUUID().toString(),
    val displayName: String = "",
    val token: String = ""
)

// TODO: Delete the credentials
data class UserCredentials(
    @SerializedName("email")
    val userName: String = "candidate1@virta.global",
    @SerializedName("code")
    val password: String = "1Candidate!"
)