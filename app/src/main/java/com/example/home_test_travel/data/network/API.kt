package com.example.home_test_travel.data.network

import com.example.home_test_travel.data.attractions.AttractionsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("{lang}/Attractions/All?")
    @Headers("Accept: application/json")
    suspend fun getAllAttractions(
        @Path("lang") lang: String,
        @Query("page") page: Int?,
        ): AttractionsResponse
}