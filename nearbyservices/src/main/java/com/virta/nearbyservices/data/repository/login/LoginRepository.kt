package com.virta.nearbyservices.data.repository.login

import com.virta.nearbyservices.data.model.LoggedInUser
import javax.inject.Inject


class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {

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
        return result
    }

}