package com.virta.nearbyservices.data.helper

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class RetrofitClient {

    // TODO: PASS THE BASE URL, ONCE READY
//    private const val BASE_URL = "https://xyz/v4/"
    private val BASE_URL = "https://apitest.virta.fi/v4/"

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getHttpClient())
            .build()
    }

    private fun getHttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    fun <S> createService(serviceClass: Class<S>?): S {
        return getRetrofit().create(serviceClass)
    }
}