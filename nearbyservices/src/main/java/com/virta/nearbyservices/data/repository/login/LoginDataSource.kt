package com.virta.nearbyservices.data.repository.login

import android.content.Context
import com.google.gson.JsonObject
import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.model.UserCredentials
import com.virta.nearbyservices.data.utils.EncryptedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSource @Inject constructor(
    @ApplicationContext val context: Context
) {

    private var loginService: LoginService =
        RetrofitClient.retrofitAuth().create(LoginService::class.java)

    // TODO: Is it ok to send Boolean back here or LiveData<Boolean>, study Transformation?
    suspend fun login(
        userName: String,
        password: String
    ): Boolean {
        return try {
            val userCredentials = UserCredentials(userName, password)
            val tokenResponse = loginService.login(
                body = userCredentials
            )
            storeToken(tokenResponse)
            true
        } catch (e: Throwable) {
            throw ResponseError("login failure", e)
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    private fun storeToken(jsonResponse: JsonObject){
        EncryptedPreferences.getInstance(context)
            .putString(EncryptedPreferences.Keys.TOKEN.name, jsonResponse.get(TOKEN_KEY).asString)
    }

    companion object {
        const val TOKEN_KEY = "access_token"
    }

}