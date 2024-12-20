package com.thecocktailapp.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.images.UrlImage
import com.mzs.core.presentation.components.compose.labels.WavyLabel
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.vo.DrinkType

@Composable
fun DetailHeaderContent(drinkType: DrinkType?, glass: String, name: String, url: String) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, start = 24.dp, top = 24.dp),
            color = colorResource(id = R.color.color_text_highlight),
            maxLines = 2,
            text = name,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            drinkType?.let { type ->
                WavyLabel(
                    buttonBackgroundColor = colorResource(id = type.buttonBackgroundColorId),
                    iconBackgroundColor = colorResource(id = type.iconBackgroundColorId),
                    iconId = type.iconId,
                    iconTint = colorResource(id = type.iconTintId),
                    textColor = colorResource(id = type.textColorId),
                    text = stringResource(id = type.labelId)
                )
            }
            if (glass.isNotEmpty()) {
                WavyLabel(
                    buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconBackgroundColor = MaterialTheme.colorScheme.secondary,
                    iconId = R.drawable.ic_cocktail,
                    iconTint = MaterialTheme.colorScheme.onSecondary,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    text = glass
                )
            }
        }
        UrlImage(
            modifier = Modifier
                .padding(end = 60.dp, start = 60.dp, top = 24.dp)
                .aspectRatio(ratio = 1f),
            contentScale = ContentScale.Crop,
            cornerRadius = 8.dp,
            url = url
        )
    }
}