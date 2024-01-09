package com.example.universitieslist.remote.repository

import com.example.universitieslist.remote.ApiService
import com.example.universitieslist.remote.model.UniversityResponse
import com.example.universitieslist.util.Constants.ERROR_FETCHING
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
                emit(response.body().orEmpty())
            } else {
                throw FetchUniversitiesException(
                    ERROR_FETCHING + "${
                        response.errorBody()?.string()
                    }"
                )
            }
        } catch (error: Exception) {
            throw FetchUniversitiesException(error.toString())
        }
    }
}

class FetchUniversitiesException(message: String) : Exception(message)
