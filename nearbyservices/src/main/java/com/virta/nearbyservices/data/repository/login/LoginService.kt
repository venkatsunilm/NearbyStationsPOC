/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.virta.nearbyservices.data.repository.login

import com.google.gson.JsonObject
import com.virta.nearbyservices.data.model.UserCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth")
    suspend fun login(
        @Body body: UserCredentials
    ): JsonObject
}