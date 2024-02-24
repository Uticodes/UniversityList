package com.example.universitieslist.util

import com.example.universitieslist.util.Constants.UNKNOWN_ERROR
import retrofit2.Response
import timber.log.Timber

suspend fun <T> performApiCall(
    apiCall: suspend () -> Response<T>,
    country: String
): Result<T?> {
    val unableToFetchMessage = "${Constants.UNABLE_TO_FETCHING} $country"

    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            val errorBody = response.errorBody()?.string() ?: UNKNOWN_ERROR
            val errorMessage = "$unableToFetchMessage - HTTP ${response.code()}: $errorBody"
            Timber.e("API Error", errorMessage)
            Result.failure(FetchUniversitiesException(unableToFetchMessage))
        }
    } catch (e: Exception) {
        Timber.e(e, "API call failed")
        Result.failure(e)
    }
}
