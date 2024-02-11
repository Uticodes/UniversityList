package com.example.universitieslist.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.universitieslist.data.remote.repository.UniversityRepository
import com.example.universitieslist.util.Constants
import com.example.universitieslist.util.DispatcherHelper
import com.example.universitieslist.util.mockUniversityResponses
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UniversityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: UniversityViewModel

    @MockK
    private lateinit var repository: UniversityRepository

    @MockK
    private lateinit var dispatcherHelper: DispatcherHelper

    private val countryName = Constants.COUNTRY_NAME
    private val expectedUniversityList = mockUniversityResponses

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxed = true)
        every { dispatcherHelper.io() } returns dispatcher
        viewModel = UniversityViewModel(repository, dispatcherHelper)
    }

    @Test
    fun `fetch universities and update universityList on success`() = runTest {
        coEvery { repository.getUniversities(countryName) } returns flowOf(expectedUniversityList)

        viewModel.fetchUniversities()
        val uiState = viewModel.uiState.value
        advanceUntilIdle()

        assertFalse(uiState.isLoading)
        assertEquals(expectedUniversityList, uiState.universityList)
    }

    @Test
    fun `fetch universities and update error message on error`() = runTest {
        val expectedError = Exception("There is an error")

        coEvery { repository.getUniversities(countryName) } throws expectedError

        viewModel.fetchUniversities()
        val uiState = viewModel.uiState.value
        advanceUntilIdle()

        assertFalse(uiState.isLoading)
        assertEquals(expectedError.toString(), uiState.errorMessage)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
