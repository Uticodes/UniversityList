package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.universitieslist.data.model.UniversityResponse
import com.example.universitieslist.ui.theme.Dimensions.dimens20
import com.example.universitieslist.ui.theme.Dimensions.dimens6

@Composable
fun UniversityList(
    universityData: List<UniversityResponse>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimens20,
                end = dimens20,
                bottom = dimens6,
            )
    ) {
        items(universityData.size) { index ->
            val university = universityData[index]
            UniversityItem(
                schoolName = university.name.orEmpty(),
                website = university.webPages?.firstOrNull().orEmpty(),
                country = university.country.orEmpty()
            )
        }
    }
}
