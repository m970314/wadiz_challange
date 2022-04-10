package com.test.wadiz.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Body(
    @SerializedName("list") val list: List<ListData>
)
