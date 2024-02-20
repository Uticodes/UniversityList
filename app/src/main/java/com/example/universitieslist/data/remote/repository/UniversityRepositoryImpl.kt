package com.example.universitieslist.data.remote.repository

import com.example.universitieslist.data.model.UniversityResponse
import com.example.universitieslist.data.remote.ApiService
import com.example.universitieslist.util.performApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UniversityRepository {

    override suspend fun getUniversities(country: String): Flow<Result<List<UniversityResponse>?>> {
        return performApiCall(
            apiCall = { apiService.getUniversities(country) },
            country = country
        )
    }
}
