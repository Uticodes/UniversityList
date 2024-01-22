package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.universitieslist.ui.theme.Dimensions.dimens20
import com.example.universitieslist.ui.theme.Dimensions.dimens6

@Composable
fun UniversityList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimens20,
                end = dimens20,
                bottom = dimens6,
            )
    ) {
        items(count = 15) {
            UniversityItem(
                schoolName = "African University of Science and Technology Coming to an end",
                website = "http://www.aust-abuja.org/",
                country = "Nigeria"
            )
        }
    }
}
