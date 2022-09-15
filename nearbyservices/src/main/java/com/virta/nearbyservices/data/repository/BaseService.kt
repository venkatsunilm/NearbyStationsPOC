package com.virta.nearbyservices.data.repository

import com.virta.nearbyservices.data.NetworkResult
import retrofit2.Response

abstract class BaseService {

    data class ParseError(
        val responseMessage: String = "",
        val responseCode: Int = 0,
        val errorBodyJson: String? = null
    ): Exception()

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): NetworkResult<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return NetworkResult.Error(ParseError(t.message.toString()))
        }

        return if (!response.isSuccessful) {
            val errorBody = response.errorBody()
            NetworkResult.Error(
                ParseError(
                    response.message(),
                    response.code(),
                    errorBody?.string() ?: ""
                )
            )
        } else {
            return if (response.body() == null) {
                NetworkResult.Error(ParseError("response.body() can't be null"))
            } else {
                NetworkResult.Success(response.body()!!)
            }
        }
    }

}