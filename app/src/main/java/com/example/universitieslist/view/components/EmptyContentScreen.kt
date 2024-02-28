package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.universitieslist.util.Constants

@Composable
fun EmptyContentScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = Constants.NO_UNIVERSITIES_FOUND,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center
    )
}
