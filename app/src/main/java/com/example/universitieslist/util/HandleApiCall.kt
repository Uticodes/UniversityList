package com.example.universitieslist.util

import com.example.universitieslist.util.Constants.UNKNOWN_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

suspend fun <T> performApiCall(
    apiCall: suspend () -> Response<T>,
    country: String,
): Flow<T> = flow {
    val unableToFetchMessage = "${Constants.UNABLE_TO_FETCHING} $country"

    try {
        val response = apiCall()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            emit(responseBody)
        } else {
            val errorBody = response.errorBody()?.string() ?: UNKNOWN_ERROR
            val errorMessage = "$unableToFetchMessage - HTTP ${response.code()}: $errorBody"
            Timber.e("API Error", errorMessage)
            throw FetchUniversitiesException(unableToFetchMessage)
        }
    } catch (error: Exception) {
        Timber.e("API Error", "$unableToFetchMessage ${error.localizedMessage}", error)
        throw FetchUniversitiesException("$unableToFetchMessage, $error")
    }
}
