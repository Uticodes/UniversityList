package com.example.universitieslist.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.universitieslist.ui.theme.UniversitiesListTheme
import com.example.universitieslist.view.UniversitiesScreen
import com.example.universitieslist.view.viewModel.UniversityViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UniversityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            UniversitiesListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Timber.d("onCreate: $uiState")

                    // Observing the university data list
//                    lifecycleScope.launchWhenStarted {
//                        viewModel.universityData.collect { universities ->
//                            // Update UI with the list of universities
//                            updateUniversitiesList(universities)
//                            Timber.d("onCreate: $universities")
//                        }
//                    }

                    // Observing the UI State for loading and error handling
//                    lifecycleScope.launchWhenStarted {
//                        viewModel.uiState.collect { uiState ->
//                            when (uiState) {
//                                is UniversityViewModel.UIState.Loading -> showLoadingIndicator()
//                                is UniversityViewModel.UIState.Success -> hideLoadingIndicator()
//                                is UniversityViewModel.UIState.Error -> {
//                                    hideLoadingIndicator()
//                                    showError(uiState.exception.message)
//                                }
//                            }
//                        }
//                    }

//                    viewModel.fetchUniversities("United States")
//                    Timber.d("onCreate State: $uiState")
                    UniversitiesScreen()
                }
            }
        }
    }
//
//    private fun updateUniversitiesList(universities: List<UniversityResponse>) {
//        // Implement the logic to update your RecyclerView/ListView/other UI component
//        // For example, if using a RecyclerView:
//        // myRecyclerViewAdapter.submitList(universities)
//        Timber.d("Update: $universities")
//    }
//
//    private fun showLoadingIndicator() {
//        // Show loading indicator
//        Timber.d("Loading: ")
//    }
//
//    private fun hideLoadingIndicator() {
//        // Hide loading indicator
//        Timber.d("hideLoadingIndicator: ")
//    }
//
//    private fun showError(message: String?) {
//        // Show error message. You might want to use a Snackbar, Toast, or a dialog.
//        Toast.makeText(this, message ?: "Error occurred", Toast.LENGTH_SHORT).show()
//    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UniversitiesListTheme {
        UniversitiesScreen()
    }
}
