package com.example.universitieslist.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.universitieslist.ui.theme.Dimensions.dimens12
import com.example.universitieslist.ui.theme.Dimensions.dimens6
import com.example.universitieslist.ui.theme.Dimensions.dimens8
import com.example.universitieslist.ui.theme.FontSize
import com.example.universitieslist.ui.theme.OffWhite
import com.example.universitieslist.ui.theme.Purple40
import com.example.universitieslist.ui.theme.White

@Composable
fun UniversityItem(schoolName: String, website: String, country: String) {
    Card(
        modifier = Modifier.padding(top = dimens12),
        elevation = CardDefaults.cardElevation(dimens6),
        colors = CardDefaults.cardColors(containerColor = Purple40)
    ) {
        Column(modifier = Modifier.padding(dimens8)) {
            TextItem(
                hintText = "Name",
                title = schoolName,
                textFont = FontSize.fontSize16
            )
            TextItem(
                hintText = "Website",
                title = website
            )
            TextItem(
                hintText = "Country",
                title = country
            )
        }
    }
}

@Composable
fun TextItem(
    hintText: String,
    title: String,
    textFont: TextUnit = FontSize.fontSize12
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$hintText:",
            style = TextStyle(
                color = OffWhite,
                fontSize = FontSize.fontSize12,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier.padding(end = dimens8)
        )
        Text(
            text = title,
            style = TextStyle(
                color = White,
                fontSize = textFont,
                fontWeight = FontWeight.W500
            )
        )
    }
}
