
package com.example.apitestsample

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/test")
    fun getTest(): Call<ApiResponse>
}
