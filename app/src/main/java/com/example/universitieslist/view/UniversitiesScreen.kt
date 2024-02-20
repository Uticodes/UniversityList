package com.example.universitieslist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universitieslist.R
import com.example.universitieslist.util.Constants.NO_UNIVERSITIES_FOUND
import com.example.universitieslist.view.components.AppToolbar
import com.example.universitieslist.view.components.UniversityList
import com.example.universitieslist.view.viewmodel.UniversityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversitiesScreen() {
    val viewModel: UniversityViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        uiState.errorMessage?.let {
            snackBarHostState.showSnackbar(it)
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
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    uiState.universityList.isNullOrEmpty() -> {
                        Text(
                            modifier = Modifier.fillMaxSize(),
                            text = NO_UNIVERSITIES_FOUND,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }

                    else -> {
                        UniversityList(universityList = uiState.universityList)
                    }
                }
            }
        }
    )
}
