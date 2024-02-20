package com.example.universitieslist.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitieslist.data.remote.repository.UniversityRepository
import com.example.universitieslist.dispatcher.DispatcherHelper
import com.example.universitieslist.util.Constants.COUNTRY_NAME
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

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        fetchUniversities()
    }

    fun fetchUniversities() {
        viewModelScope.launch(dispatcher.io()) {
            _uiState.update { state -> state.copy(isLoading = true) }
            universityRepository.getUniversities(COUNTRY_NAME).collect { result ->
                result.onSuccess { universities ->
                    _uiState.update { state -> state.copy(isLoading = false, universityList = universities) }
                }.onFailure { error ->
                    _uiState.update { state -> state.copy(isLoading = false, errorMessage = error.message) }
                }
            }
        }
    }
}
