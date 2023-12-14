package com.example.universitieslist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.universitieslist.R
import com.example.universitieslist.view.components.AppToolbar
import com.example.universitieslist.view.components.UniversityList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversitiesScreen() {

    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(R.string.app_name),
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.TopStart
            ) {
                UniversityList()
            }
        }
    )
}
