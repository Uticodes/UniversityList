package com.example.universitieslist.data.remote.repository

import com.example.universitieslist.data.model.UniversityResponse
import com.example.universitieslist.data.remote.ApiService
import com.example.universitieslist.util.Constants.COUNTRY_NAME
import com.example.universitieslist.util.Constants.UNABLE_TO_FETCHING
import com.example.universitieslist.util.Constants.UNKNOWN_ERROR
import com.example.universitieslist.util.FetchUniversitiesException
import com.example.universitieslist.util.mockUniversityResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertFailsWith

class UniversityRepositoryImplTest {

    private val mockApiService = mockk<ApiService>()
    private lateinit var universityRepositoryImpl: UniversityRepositoryImpl

    @Before
    fun setUp() {
        universityRepositoryImpl = UniversityRepositoryImpl(mockApiService)
    }

    @Test
    fun `getUniversities list when response is successful`() = runTest {
        val countryName = COUNTRY_NAME
        val expectedUniversityList = mockUniversityResponses
        val fakeResponse = Response.success(expectedUniversityList)

        coEvery { mockApiService.getUniversities(eq(countryName)) } returns fakeResponse

        val resultList = universityRepositoryImpl.getUniversities(countryName).toList()

        assertTrue("Result list should not be empty", resultList.isNotEmpty())
        assertEquals(
            "The returned university list should match the expected mock data",
            expectedUniversityList,
            resultList.first()
        )
    }

    @Test
    fun `getUniversities throws FetchUniversitiesException on API error response`() = runTest {
        val errorResponseBody = mockk<ResponseBody>(relaxed = true)
        val errorResponse = Response.error<List<UniversityResponse>>(500, errorResponseBody)

        coEvery { mockApiService.getUniversities(any()) } returns errorResponse

        val exception = assertFailsWith<FetchUniversitiesException> {
            universityRepositoryImpl.getUniversities(COUNTRY_NAME).toList()
        }

        assertTrue(
            "Error message should indicate an issue fetching universities",
            exception.message?.contains(UNABLE_TO_FETCHING) == true
        )
    }

    @Test
    fun `getUniversities throws FetchUniversitiesException on unexpected exception`() = runTest {
        coEvery { mockApiService.getUniversities(any()) } throws RuntimeException(UNKNOWN_ERROR)

        val exception = assertFailsWith<FetchUniversitiesException> {
            universityRepositoryImpl.getUniversities(COUNTRY_NAME).toList()
        }

        assertTrue(
            "Error message should indicate an unexpected error",
            exception.message?.contains(UNABLE_TO_FETCHING) == true
        )
    }
}
