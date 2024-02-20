package com.example.universitieslist.data.remote.repository

import com.example.universitieslist.data.model.UniversityResponse
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    suspend fun getUniversities(country: String): Flow<Result<List<UniversityResponse>?>>
}
