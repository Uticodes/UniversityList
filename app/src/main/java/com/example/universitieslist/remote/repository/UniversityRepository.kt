package com.example.universitieslist.remote.repository

import com.example.universitieslist.remote.model.UniversityResponse
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    suspend fun getUniversities(country: String): Flow<List<UniversityResponse>>
}
