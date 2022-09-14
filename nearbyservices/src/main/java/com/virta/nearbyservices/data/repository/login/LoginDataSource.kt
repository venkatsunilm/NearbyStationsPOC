package com.virta.nearbyservices.data.repository.login

import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.model.UserCredentials
import com.virta.nearbyservices.data.utils.TOKEN_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSource @Inject constructor() {

    private var loginService: LoginService = RetrofitClient.CreateService.create(LoginService::class.java)

    // TODO: Is it ok to send Boolean back here or LiveData<Boolean>, study Transformation?
    suspend fun login(
        userName: String,
        password: String
    ): String {
        return try {
            val userCredentials = UserCredentials(userName, password)
            val tokenJsonResponse = loginService.login(
                body = userCredentials
            )
            tokenJsonResponse.get(TOKEN_KEY).asString
        } catch (e: Throwable) {
            throw ResponseError("login failure", e)
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

}