package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchToolbar(
    title: String,
    inputValue: String,
    onValueChange: (String) -> Unit,
    onClose: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = title) },
        actions = {
            if (isExpanded) {
                SearchInputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = inputValue,
                    onValueChange = onValueChange,
                    onClose = {
                        onClose()
                        isExpanded = false
                    }
                )
            } else {
                SearchIcon(onClick = { isExpanded = true })
            }
        },
    )
}
