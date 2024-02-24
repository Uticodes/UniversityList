package com.example.universitieslist.data.model

data class UniversityModel(
    val name: String,
    val country: String,
    val webPages: List<String>,
    val alphaTwoCode: String,
    val stateProvince: String,
    val domains: List<String>,
)

fun UniversityResponse.toDisplayModel(): UniversityModel {
    return UniversityModel(
        name = this.name.orEmpty(),
        country = this.country.orEmpty(),
        webPages = this.webPages ?: emptyList(),
        alphaTwoCode = this.alphaTwoCode.orEmpty(),
        stateProvince = this.stateProvince.orEmpty(),
        domains = this.domains ?: emptyList()
    )
}
