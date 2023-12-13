package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.universitieslist.ui.theme.Dimensions.dimens10
import com.example.universitieslist.ui.theme.Dimensions.dimens15
import com.example.universitieslist.ui.theme.FontSize.fontSize18
import com.example.universitieslist.ui.theme.Purple40

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchInputField(
    value: String,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BasicTextField(
        modifier = modifier
            .padding(dimens10)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    keyboardController?.show()
                }
            },
        value = value,
        textStyle = TextStyle(color = Color.White, fontSize = fontSize18),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.None
        ),
        keyboardActions = KeyboardActions(
            onSearch = { }
        ),
        visualTransformation = VisualTransformation.None,
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Purple40
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(start = dimens15)
                ) {
                    innerTextField()
                    CloseIcon(onClick = onClose)
                }
            }
        }
    )
}
