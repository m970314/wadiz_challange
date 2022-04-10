package com.test.wadiz.request

import com.test.wadiz.data.Body
import com.test.wadiz.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("search")
    fun requestSearch(@Query("keyword") keyword: String): Call<SearchResponse>
}