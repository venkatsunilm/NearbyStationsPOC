package com.virta.nearbyservices.data.helper

import android.content.Context
import com.virta.nearbyservices.data.utils.EncryptedPreferences
import javax.inject.Inject

class EncryptedPreferencesHelper @Inject constructor(private val applicationContext: Context) {

    fun saveToken(token: String): Boolean {
        return EncryptedPreferences.getInstance(applicationContext)
            .putString(EncryptedPreferences.Keys.TOKEN.name, token)
    }

    fun isAuthenticated() {
        // TODO: Check if the token is still valid

    }

    // TODO: Rework on this once again once the Hilt provides and bind concepts are implemented
    companion object {
        private var helper: EncryptedPreferencesHelper? = null
        fun getInstance(context: Context): EncryptedPreferencesHelper {
            if (helper == null) {
                helper = EncryptedPreferencesHelper(context)
            }
            return helper!!
        }
    }

}