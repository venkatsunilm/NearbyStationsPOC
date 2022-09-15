package com.virta.nearbyservices.data.repository.login

import android.content.Context
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.helper.EncryptedPreferencesHelper
import com.virta.nearbyservices.data.utils.TOKEN_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val dataSource: LoginDataSource
) {
    val isLoggedIn: Boolean
        get() =  EncryptedPreferencesHelper.getInstance(context).isAuthenticated()

    suspend fun login(username: String, password: String): NetworkResult<Boolean> {
        return when (val result = dataSource.login(username, password)) {
            is NetworkResult.Success -> {
                NetworkResult.Success(
                    EncryptedPreferencesHelper.getInstance(context)
                        .saveToken(result.data.get(TOKEN_KEY).asString)
                )
            }
            is NetworkResult.Error -> {
                NetworkResult.Error(result.exception)
            }
        }
    }

}