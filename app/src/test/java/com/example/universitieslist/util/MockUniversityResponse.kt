package com.example.universitieslist.util

import com.example.universitieslist.data.model.UniversityModel
import com.example.universitieslist.data.model.UniversityResponse

val mockUniversityResponses = listOf(
    UniversityResponse(
        webPages = listOf("http://www.testuniversity1.com"),
        alphaTwoCode = "TU",
        domains = listOf("test_university1.com"),
        country = "Ghana",
        name = "Fake University One",
        stateProvince = "Fake Province 1"
    ),
    UniversityResponse(
        webPages = listOf("http://www.testuniversity2.com"),
        alphaTwoCode = "NU",
        domains = listOf("test_university2.com"),
        country = "Nigeria",
        name = "Fake University Two",
        stateProvince = "Fake Province 2"
    )
)

val mockUniversityModelList = listOf(
    UniversityModel(
        webPages = listOf("http://www.testuniversity1.com"),
        alphaTwoCode = "TU",
        domains = listOf("test_university1.com"),
        country = "Ghana",
        name = "Fake University One",
        stateProvince = "Fake Province 1"
    ),
    UniversityModel(
        webPages = listOf("http://www.testuniversity2.com"),
        alphaTwoCode = "NU",
        domains = listOf("test_university2.com"),
        country = "Nigeria",
        name = "Fake University Two",
        stateProvince = "Fake Province 2"
    )
)
