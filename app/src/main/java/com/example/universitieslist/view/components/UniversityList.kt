package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.universitieslist.data.model.UniversityModel
import com.example.universitieslist.ui.theme.Dimensions.dimens20

@Composable
fun UniversityList(
    universityList: List<UniversityModel>?,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimens20,
                end = dimens20,
                bottom = dimens20,
            )
    ) {
        universityList?.forEach { university ->
            val hasContent = university.name.isNotEmpty() ||
                university.webPages.isNotEmpty() ||
                university.country.isNotEmpty()
            if (hasContent) {
                item {
                    UniversityItem(
                        schoolName = university.name,
                        website = university.webPages.firstOrNull().orEmpty(),
                        country = university.country
                    )
                }
            }
        }
    }
}
