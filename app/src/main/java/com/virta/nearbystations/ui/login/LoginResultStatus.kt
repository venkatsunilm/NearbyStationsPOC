package com.virta.nearbystations.ui.login

// TODO: Make sealed class and make all status classes generic
data class LoginResultStatus(
    val success: Boolean = false,
    val errorMessage: String? = null
)
