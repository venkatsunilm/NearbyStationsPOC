/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.virta.nearbyservices.data.repository.login

import com.google.gson.JsonObject
import com.virta.nearbyservices.data.model.UserCredentials
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    companion object {
        private const val LOGIN_AUTH = "auth"
    }

    @POST(LOGIN_AUTH)
    suspend fun login(
        @Header("accept") accept: String = "application/json",
        @Header("Content-Type") contentType: String = "application/json",
        @Body body: UserCredentials
    ): JsonObject
}