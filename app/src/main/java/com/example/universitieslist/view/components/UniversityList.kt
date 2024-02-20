package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.universitieslist.data.model.UniversityResponse
import com.example.universitieslist.ui.theme.Dimensions.dimens20

@Composable
fun UniversityList(
    universityList: List<UniversityResponse>?,
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
            val hasContent = university.name.isNullOrEmpty().not() ||
                university.webPages.isNullOrEmpty().not() ||
                university.country.isNullOrEmpty().not()
            if (hasContent) {
                item {
                    UniversityItem(
                        schoolName = university.name.orEmpty(),
                        website = university.webPages?.firstOrNull().orEmpty(),
                        country = university.country.orEmpty()
                    )
                }
            }
        }
    }
}
