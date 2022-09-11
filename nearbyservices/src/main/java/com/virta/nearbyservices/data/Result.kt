package com.virta.nearbyservices.data

// TODO: use this
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

class ResponseError(message: String, cause: Throwable) : Throwable(message, cause)

data class ResponseDataModel<T>(val isSuccess: Boolean?, val data: T?, val message: String?)