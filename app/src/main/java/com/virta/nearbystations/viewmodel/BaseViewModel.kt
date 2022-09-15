package com.virta.nearbystations.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected suspend fun <T : Any> safeCall(call: suspend () -> T): T? {
        try {
            return call.invoke()
        } catch (e: Throwable) {
//            parseUIError(e.message.toString())
        }
        return null
    }
}