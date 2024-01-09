package com.example.universitieslist.view.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitieslist.remote.model.UniversityResponse
import com.example.universitieslist.remote.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val universityRepository: UniversityRepository
) : ViewModel() {

    private val _universityData = MutableStateFlow<List<UniversityResponse>>(emptyList())
    val universityData: StateFlow<List<UniversityResponse>> = _universityData

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        fetchUniversities("United State")
    }

    private fun fetchUniversities(country: String) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                universityRepository.getUniversities(country).collect { universities ->
                    _universityData.value = universities
                    Timber.d("Result: $universities")
                    _uiState.value = UIState.Success
                }
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e)
            }
        }
    }

    sealed class UIState {
        object Loading : UIState()
        object Success : UIState()
        data class Error(val exception: Throwable) : UIState()
    }
}
