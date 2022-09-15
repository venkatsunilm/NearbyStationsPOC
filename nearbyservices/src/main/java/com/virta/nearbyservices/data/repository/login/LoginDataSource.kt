package com.virta.nearbyservices.data.repository.login

import com.google.gson.JsonObject
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.helper.RetrofitClient
import com.virta.nearbyservices.data.repository.BaseService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSource @Inject constructor() : BaseService() {

    private var loginService: LoginService =
        RetrofitClient.CreateService.create(LoginService::class.java)

    suspend fun login(
        userName: String,
        password: String
    ): NetworkResult<JsonObject> {
        val userDetailsDto = UserDetailsDto(
            userName,
            password
        )
        return apiCall { loginService.login(body = userDetailsDto) }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}