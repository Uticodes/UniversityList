package com.example.universitieslist.data.remote.repository

import com.example.universitieslist.data.model.UniversityResponse
import com.example.universitieslist.data.remote.ApiService
import com.example.universitieslist.util.Constants.UNABLE_TO_FETCHING
import com.example.universitieslist.util.Constants.UNKNOWN_ERROR
import com.example.universitieslist.util.FetchUniversitiesException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UniversityRepository {

    override suspend fun getUniversities(country: String): Flow<List<UniversityResponse>> = flow {
        val unableToFetchError = "$UNABLE_TO_FETCHING $country"
        try {
            val response = apiService.getUniversities(country)
            if (response.isSuccessful && response.body() != null) {
                emit(response.body().orEmpty())
            } else {
                val errorBody = response.errorBody()?.string() ?: UNKNOWN_ERROR
                val errorMessage = "$unableToFetchError - HTTP ${response.code()}: $errorBody"
                Timber.e("UniversityRepository", errorMessage)
                throw FetchUniversitiesException(unableToFetchError)
            }
        } catch (error: Exception) {
            Timber.e("UniversityRepository", "$unableToFetchError ${error.localizedMessage}", error)
            throw FetchUniversitiesException("$unableToFetchError, $error")
        }
    }
}
