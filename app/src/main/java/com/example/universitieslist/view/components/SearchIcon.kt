package com.example.universitieslist.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.universitieslist.R

@Composable
fun SearchIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
    }
}
