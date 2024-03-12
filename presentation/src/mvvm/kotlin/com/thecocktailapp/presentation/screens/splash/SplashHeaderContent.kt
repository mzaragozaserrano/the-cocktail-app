package com.thecocktailapp.presentation.screens.splash

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.components.images.UrlImage

@Composable
fun SplashHeaderContent(modifier: Modifier = Modifier, urlImage: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(bottomEnd = 48.dp)
    ) {
        UrlImage(
            modifier = Modifier
                .padding(all = 24.dp)
                .aspectRatio(1f),
            cornerRadius = 8.dp,
            url = urlImage
        )
    }
}
