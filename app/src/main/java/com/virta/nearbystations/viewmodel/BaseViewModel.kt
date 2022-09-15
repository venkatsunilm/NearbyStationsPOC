package com.virta.nearbystations.viewmodel

import androidx.lifecycle.ViewModel
import com.virta.nearbyservices.data.NetworkResult

abstract class BaseViewModel : ViewModel() {

//    data class parseUIError(
//        val responseMessage: String = "",
//        val responseCode: Int = 0,
//        val errorBodyJson: String? = null
//    ) : Exception()

    protected suspend fun <T : Any> safeCall(call: suspend () -> T): T? {
        try {
            return call.invoke()
        } catch (e: Throwable) {
//            parseUIError(e.message.toString())
        }
        return null
    }
}