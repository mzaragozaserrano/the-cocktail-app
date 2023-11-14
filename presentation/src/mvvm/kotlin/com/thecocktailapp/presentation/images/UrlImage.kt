package com.thecocktailapp.presentation.images

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.thecocktailapp.ui.R

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    cornerRadius: Dp? = null,
    @DrawableRes defaultImage: Int? = R.drawable.loading_img,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .transformations(cornerRadius?.let { RoundedCornersTransformation(radius = LocalDensity.current.density * cornerRadius.value) }
                ?: RoundedCornersTransformation(radius = 0f))
            .build(),
        placeholder = defaultImage?.let { painterResource(id = it) },
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}