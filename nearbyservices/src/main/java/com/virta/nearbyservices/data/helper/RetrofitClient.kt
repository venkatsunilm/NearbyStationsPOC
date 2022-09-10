package com.virta.nearbyservices.data.helper

import com.google.gson.GsonBuilder
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // TODO: PASS THE BASE URL, ONCE READY
//    private const val BASE_URL = "https://xyz/v4/"
    private const val BASE_URL = "https://apitest.virta.fi/v4/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun retrofitAuth(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createClientAuth())
            .build()
    }

    fun createClientAuth(): OkHttpClient{
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    }
}