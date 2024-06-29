package com.example.home_test_travel.data.network

object ApiConfig {

    interface Endpoint {
        val api: String
    }

    object Production : Endpoint {
        override val api = "https://www.travel.taipei/open-api/"

    }

    object PreProduction : Endpoint {
        override val api = "https://www.travel.taipei/open-api/"
    }


    object Staging : Endpoint {
        override val api = "https://www.travel.taipei/open-api/"

    }
    fun api(): String {

//        val endpoint = when (endpointMode) {
//            ENDPOINT_MODE_PROD -> Production
//            ENDPOINT_MODE_PREPROD -> PreProduction
//            ENDPOINT_MODE_STAGING -> Staging
//            else -> Production
//        }

        val endpoint = Staging
        return endpoint.api
    }
}