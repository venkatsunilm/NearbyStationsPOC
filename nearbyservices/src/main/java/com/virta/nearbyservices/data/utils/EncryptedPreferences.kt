/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.virta.nearbyservices.data.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

// TODO: use Hilt for context once implemented
class EncryptedPreferences(private val applicationContext: Context) {
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

    fun putString(key: String, text: String): Boolean {
        with(sharedPreferencesEditor) {
            return putString(key, text).commit()
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

    fun clearPreference() {
        with(sharedPreferencesEditor) { clear().apply() }
        if (sharedPreferenceObject.contains(sharedPreferencesName)) {
            with(sharedPreferencesEditor) { remove(sharedPreferencesName).commit() }
        }
    }

    companion object {
        private lateinit var masterKeyAlias: MasterKey
        private var encryptedPreferences: EncryptedPreferences? = null
        fun getInstance(context: Context): EncryptedPreferences {
            if (encryptedPreferences == null) {
                masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
                encryptedPreferences = EncryptedPreferences(context)
            }
            return encryptedPreferences!!
        }
    }

}