package com.example.universitieslist.util

import com.example.universitieslist.data.model.UniversityResponse

data class UIState(
    val isLoading: Boolean = false,
    val universityList: List<UniversityResponse>? = emptyList(),
    val errorMessage: String? = null
)
