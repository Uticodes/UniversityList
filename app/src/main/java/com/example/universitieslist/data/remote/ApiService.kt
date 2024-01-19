package com.example.universitieslist.data.remote

import com.example.universitieslist.data.model.UniversityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getUniversities(@Query("country") country: String): Response<List<UniversityResponse>>
}
