package com.test.wadiz.request

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRequestFactory {
    private const val baseUrl = "https://nr89frnqk0.execute-api.ap-northeast-2.amazonaws.com/dev/"
    val gson: Gson = GsonBuilder().setLenient().create()
    val retrofit: Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }).build())
        .build()
        .create(Service::class.java)
}