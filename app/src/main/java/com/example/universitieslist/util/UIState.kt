package com.example.universitieslist.util

sealed class UIState {
    object Loading : UIState()
    object Success : UIState()
    data class Error(val exception: Throwable) : UIState()
}
