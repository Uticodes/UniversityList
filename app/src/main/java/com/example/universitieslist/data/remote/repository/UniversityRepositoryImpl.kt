package com.example.universitieslist.data.remote.repository

import com.example.universitieslist.data.model.UniversityModel
import com.example.universitieslist.data.model.toDisplayModel
import com.example.universitieslist.data.remote.ApiService
import com.example.universitieslist.util.performApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UniversityRepository {

    override suspend fun getUniversities(country: String): Flow<Result<List<UniversityModel>>> = flow {
        emit(
            performApiCall(
                apiCall = { apiService.getUniversities(country) },
                country = country
            ).map { responseData ->
                responseData?.map { it.toDisplayModel() } ?: emptyList()
            }
        )
    }
}
