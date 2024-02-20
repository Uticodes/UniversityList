package com.example.universitieslist.util

import com.example.universitieslist.util.Constants.UNKNOWN_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

suspend fun <T> performApiCall(
    apiCall: suspend () -> Response<T>,
    country: String,
): Flow<Result<T?>> = flow {
    val unableToFetchMessage = "${Constants.UNABLE_TO_FETCHING} $country"

    val response = apiCall()
    if (response.isSuccessful) {
        emit(Result.success(response.body()))
    } else {
        val errorBody = response.errorBody()?.string() ?: UNKNOWN_ERROR
        val errorMessage = "$unableToFetchMessage - HTTP ${response.code()}: $errorBody"
        Timber.e("API Error", errorMessage)
        emit(Result.failure(FetchUniversitiesException(unableToFetchMessage)))
    }
}.catch { e ->
    emit(Result.failure(e))
}
