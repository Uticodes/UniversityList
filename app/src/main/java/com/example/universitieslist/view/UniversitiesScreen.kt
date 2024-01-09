package com.example.universitieslist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universitieslist.R
import com.example.universitieslist.util.Constants.UNKNOWN_ERROR
import com.example.universitieslist.util.UIState
import com.example.universitieslist.view.components.AppToolbar
import com.example.universitieslist.view.components.UniversityList
import com.example.universitieslist.view.viewModel.UniversityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversitiesScreen() {
    val viewModel: UniversityViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val universityData by viewModel.universityData.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        if (uiState is UIState.Error) {
            val errorMessage =
                (uiState as UIState.Error).exception.message ?: UNKNOWN_ERROR
            snackBarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(R.string.app_name),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.TopStart
            ) {
                when (uiState) {
                    is UIState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is UIState.Success -> {
                        UniversityList(universityData)
                    }
                    else -> Unit
                }
            }
        }
    )
}
