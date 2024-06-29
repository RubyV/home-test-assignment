package com.example.home_test_travel.data.attractions

import com.google.gson.annotations.SerializedName

data class AttractionsResponse(
    @SerializedName("total")
    val total: Int? = 0,
    @SerializedName("data")
    val data: List<AttractionsData>? = emptyList()
)