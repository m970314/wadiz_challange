package com.test.wadiz.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("body") val body: Body
)
