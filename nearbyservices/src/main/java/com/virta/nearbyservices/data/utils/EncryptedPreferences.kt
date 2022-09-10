/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.virta.nearbyservices.data.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

// TODO: Change this class and use Hilt for context
class EncryptedPreferences() {
    private val sharedPreferencesName = "com.fsecure.mycolorapp"
    private val sharedPreferenceObject = EncryptedSharedPreferences.create(
        applicationContext,
        sharedPreferencesName,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private var sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferenceObject.edit()

    enum class Keys {
        TOKEN,
        DATA_ID
    }

    fun putString(key: String, text: String) {
        with(sharedPreferencesEditor) {
            putString(key, text).apply()
        }
    }

    fun getString(str: Keys): String {
        return sharedPreferenceObject.getString(str.name, "")!!
    }

    fun putLong(key: String, value: Long) {
        with(sharedPreferencesEditor) {
            putLong(key, value).apply()
        }
    }

    fun getLong(key: String): Long {
        return sharedPreferenceObject.getLong(key, 0)

    }

    //    TODO: To clear the preferences on Logout
    fun clearPreference() {
        with(sharedPreferencesEditor) { clear().apply() }
        if (sharedPreferenceObject.contains(sharedPreferencesName)) {
            with(sharedPreferencesEditor) { remove(sharedPreferencesName).commit() }
        }
    }

    companion object {
        private lateinit var masterKeyAlias: MasterKey
        private var encryptedPreferences: EncryptedPreferences? = null
        private lateinit var applicationContext: Context
        fun getInstance(context: Context): EncryptedPreferences {
            if (encryptedPreferences == null) {
                applicationContext = context
                masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
                encryptedPreferences = EncryptedPreferences()
            }
            return encryptedPreferences!!
        }
    }

}