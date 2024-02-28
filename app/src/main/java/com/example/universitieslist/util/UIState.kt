package com.example.universitieslist.util

import com.example.universitieslist.data.model.UniversityModel

data class UIState(
    val isLoading: Boolean = false,
    val universityList: List<UniversityModel>? = emptyList(),
    val errorMessage: String? = null
)
