package com.example.universitieslist.data.remote.repository

import com.example.universitieslist.data.remote.ApiService
import com.example.universitieslist.util.Constants.COUNTRY_NAME
import com.example.universitieslist.util.FetchUniversitiesException
import com.example.universitieslist.util.mockUniversityModelList
import com.example.universitieslist.util.mockUniversityResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UniversityRepositoryImplTest {

    private val mockApiService = mockk<ApiService>()
    private lateinit var repository: UniversityRepositoryImpl

    @Before
    fun setUp() {
        repository = UniversityRepositoryImpl(mockApiService)
    }

    @Test
    fun `getUniversities list when response is successful`() = runTest {
        coEvery { mockApiService.getUniversities(COUNTRY_NAME) } returns Response.success(
            mockUniversityResponses
        )

        val result = repository.getUniversities(COUNTRY_NAME).first()
        assertTrue(result.isSuccess)
        result.onSuccess { universities ->
            assertEquals(mockUniversityModelList, universities)
        }
    }

    @Test
    fun `getUniversities throws error message on API error response`() = runTest {
        coEvery { mockApiService.getUniversities(any()) } returns
            Response.error(500, "School Not Found".toResponseBody())

        val result = repository.getUniversities(COUNTRY_NAME).first()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is FetchUniversitiesException)
    }
}
