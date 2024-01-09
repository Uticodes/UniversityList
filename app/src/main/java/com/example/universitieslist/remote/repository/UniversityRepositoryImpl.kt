package com.example.universitieslist.remote.repository

import com.example.universitieslist.remote.ApiService
import com.example.universitieslist.remote.model.UniversityResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UniversityRepository {

    override suspend fun getUniversities(country: String): Flow<List<UniversityResponse>> = flow {
        try {
            val response = apiService.getUniversities(country)
            if (response.isSuccessful && response.body() != null) {
                emit(response.body()!!)
            } else {
                throw FetchUniversitiesException("Error fetching universities: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw FetchUniversitiesException(e.toString())
        }
    }
}

class FetchUniversitiesException(message: String) : Exception(message)
