package com.example.home_test_travel.data.network

object ApiConfig {

    interface Endpoint {
        val api: String
    }

    object Staging : Endpoint {
        override val api = "https://www.travel.taipei/open-api/"

    }
    fun api(): String {
        return Staging.api
    }
}