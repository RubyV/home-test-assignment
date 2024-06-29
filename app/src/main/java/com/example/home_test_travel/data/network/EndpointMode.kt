package com.example.home_test_travel.data.network

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(ENDPOINT_MODE_STAGING, ENDPOINT_MODE_PREPROD, ENDPOINT_MODE_PROD)
annotation class EndpointMode

const val ENDPOINT_MODE_PROD = 99
const val ENDPOINT_MODE_PREPROD = 88
const val ENDPOINT_MODE_STAGING = 77

fun Int.endpointModeName(): String {
    return when (this) {
        ENDPOINT_MODE_PROD -> "Prod Endpoint"
        ENDPOINT_MODE_PREPROD -> "PreProd Endpoint"
        ENDPOINT_MODE_STAGING -> "Staging Endpoint"
        else -> "Unknown"
    }
}
