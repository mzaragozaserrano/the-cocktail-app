package com.thecocktailapp.presentation.screens.splash

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.images.UrlImage

@Composable
fun SplashHeaderContent(urlImage: String) {
    UrlImage(
        modifier = Modifier
            .padding(all = 24.dp)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
        cornerRadius = 24.dp,
        url = urlImage
    )
}
