package com.thecocktailapp.presentation.screens.details

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.images.UrlImage
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeBlackText
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.ui.R

@Composable
fun DetailHeaderContent(drink: DrinkVO) {

    ExtraLargeBlackText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp),
        color = colorResource(id = R.color.color_text_highlight),
        maxLines = 2,
        text = drink.name.uppercase(),
        textAlign = TextAlign.Center
    )
    UrlImage(
        modifier = Modifier
            .padding(horizontal = 60.dp)
            .aspectRatio(ratio = 1f),
        cornerRadius = 8.dp,
        defaultImage = R.drawable.loading_img,
        url = drink.urlImage
    )

}