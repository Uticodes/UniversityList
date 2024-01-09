package com.example.universitieslist.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UniversityResponse(
    val webPages: List<String>? = emptyList(),
    val alphaTwoCode: String? = null,
    val domains: List<String>? = emptyList(),
    val country: String? = null,
    val name: String? = null,
    val stateProvince: String? = null
)
