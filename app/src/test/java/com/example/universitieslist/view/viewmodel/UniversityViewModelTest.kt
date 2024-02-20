package com.example.universitieslist.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.universitieslist.data.remote.repository.UniversityRepository
import com.example.universitieslist.dispatcher.DispatcherHelperImplTest
import com.example.universitieslist.util.Constants
import com.example.universitieslist.util.mockUniversityResponses
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UniversityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: UniversityViewModel
    private val dispatcherHelper = DispatcherHelperImplTest()

    @MockK
    private lateinit var repository: UniversityRepository

    private val countryName = Constants.COUNTRY_NAME
    private val expectedUniversityList = mockUniversityResponses

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = UniversityViewModel(repository, dispatcherHelper)
    }

    @Test
    fun `fetch universities and update UI state correctly on success`() = runTest {
        coEvery { repository.getUniversities(countryName) } returns flowOf(Result.success(expectedUniversityList))

        viewModel.fetchUniversities()
        val uiState = viewModel.uiState.value
        advanceUntilIdle()

        assertFalse(uiState.isLoading)
        assertEquals(expectedUniversityList, uiState.universityList)
        assertNull(viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `fetch universities and update error message on error`() = runTest {
        val expectedError = "There is an error"

        coEvery { repository.getUniversities(countryName) } returns
            flowOf(Result.failure(RuntimeException(expectedError)))

        viewModel.fetchUniversities()
        val uiState = viewModel.uiState.value
        advanceUntilIdle()

        assertFalse(uiState.isLoading)
        assertEquals(expectedError, uiState.errorMessage)
    }
}
