package com.example.universitieslist.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UniversityResponse(
    @Json(name = "web_pages")
    val webPages: List<String>? = emptyList(),
    @Json(name = "alpha_two_code")
    val alphaTwoCode: String? = null,
    val domains: List<String>? = emptyList(),
    val country: String? = null,
    val name: String? = null,
    @Json(name = "state-province")
    val stateProvince: String? = null
)
