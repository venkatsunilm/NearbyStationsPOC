package com.virta.nearbyservices.data.repository.login

import android.content.Context
import com.virta.nearbyservices.data.helper.EncryptedPreferencesHelper
import com.virta.nearbyservices.data.model.LoggedInUser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val dataSource: LoginDataSource
) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Boolean {
        // handle login
        val result = dataSource.login(username, password)
        return EncryptedPreferencesHelper.getInstance(context).saveToken(result)
    }

}