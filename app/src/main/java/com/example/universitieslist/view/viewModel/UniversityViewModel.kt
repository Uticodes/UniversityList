package com.example.universitieslist.view.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitieslist.data.model.UniversityResponse
import com.example.universitieslist.data.remote.repository.UniversityRepository
import com.example.universitieslist.util.Constants.COUNTRY_NAME
import com.example.universitieslist.util.Constants.NO_UNIVERSITIES_FOUND
import com.example.universitieslist.util.DispatcherHelper
import com.example.universitieslist.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val universityRepository: UniversityRepository,
    private val dispatcher: DispatcherHelper
) : ViewModel() {

    private val _universityData = MutableStateFlow<List<UniversityResponse>>(emptyList())
    val universityData: StateFlow<List<UniversityResponse>> = _universityData

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        fetchUniversities()
    }

    private fun fetchUniversities() {
        viewModelScope.launch(dispatcher.io()) {
            _uiState.update { UIState.Loading }
            try {
                universityRepository.getUniversities(COUNTRY_NAME).collect { universities ->
                    if (universities.isNotEmpty()) {
                        _universityData.value = universities
                        _uiState.update { UIState.Success }
                    } else {
                        _uiState.update { UIState.Error(Exception(NO_UNIVERSITIES_FOUND)) }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { UIState.Error(e) }
            }
        }
    }
}
