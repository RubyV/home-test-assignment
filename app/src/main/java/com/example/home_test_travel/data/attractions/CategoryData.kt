package com.example.home_test_travel.data.attractions

import com.google.gson.annotations.SerializedName

data class CategoryData (
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
)