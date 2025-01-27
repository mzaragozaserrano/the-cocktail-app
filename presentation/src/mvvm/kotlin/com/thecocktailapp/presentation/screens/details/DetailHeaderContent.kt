package com.thecocktailapp.presentation.screens.details

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.images.UrlImage
import com.mzs.core.presentation.components.compose.labels.WavyLabel
import com.mzs.core.presentation.components.compose.utils.Adapter
import com.mzs.core.presentation.utils.extensions.conditional
import com.mzs.core.presentation.utils.generic.ItemOrientation
import com.mzs.core.presentation.utils.generic.emptyText
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.vo.DrinkType

@Composable
fun DetailHeaderContent(
    drinkType: DrinkType?,
    glass: String,
    ingredients: List<String>,
    name: String,
    url: String
) {

    val density = LocalDensity.current
    var imageSize by remember { mutableStateOf(value = DpSize.Zero) }
    var isFlipped by remember { mutableStateOf(value = false) }
    var isLoading by remember { mutableStateOf(value = false) }
    var showBack by remember { mutableStateOf(value = false) }

    val animatedRotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing),
        label = emptyText
    )

    LaunchedEffect(animatedRotation) {
        when {
            animatedRotation >= 45f && animatedRotation < 135f && showBack.not() -> {
                showBack = true
            }

            animatedRotation < 45f && showBack -> {
                showBack = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, start = 24.dp, top = 24.dp),
            color = colorResource(id = R.color.color_text_highlight),
            maxLines = 2,
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            modifier = Modifier.padding(end = 16.dp, start = 16.dp, top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {
            drinkType?.let { type ->
                WavyLabel(
                    buttonBackgroundColor = colorResource(id = type.buttonBackgroundColorId),
                    iconBackgroundColor = colorResource(id = type.iconBackgroundColorId),
                    iconId = type.iconId,
                    iconTint = colorResource(id = type.iconTintId),
                    textColor = colorResource(id = type.textColorId),
                    text = stringResource(id = type.labelId),
                    textStyle = MaterialTheme.typography.labelLarge
                )
            }
            if (glass.isNotEmpty()) {
                WavyLabel(
                    buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconBackgroundColor = MaterialTheme.colorScheme.secondary,
                    iconId = R.drawable.ic_cocktail,
                    iconTint = MaterialTheme.colorScheme.onSecondary,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    text = glass,
                    textStyle = MaterialTheme.typography.labelLarge
                )
            }
        }

        Box(
            modifier = Modifier
                .conditional(condition = imageSize != DpSize.Zero) {
                    size(width = imageSize.width, height = imageSize.height)
                }
                .padding(all = 24.dp)
                .graphicsLayer {
                    cameraDistance = 16 * this.density
                    rotationY = animatedRotation
                    scaleX = if (animatedRotation > 90f) -1f else 1f
                }
                .clickable {
                    isFlipped = isFlipped.not()
                }
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .conditional(condition = isLoading.not()) {
                    background(color = MaterialTheme.colorScheme.secondaryContainer)
                },
            contentAlignment = Alignment.Center,
            content = {
                when {
                    animatedRotation < 90f -> {
                        UrlImage(
                            modifier = Modifier.aspectRatio(ratio = 1f),
                            animationId = R.raw.image_loading,
                            contentScale = ContentScale.Crop,
                            cornerRadius = 8.dp,
                            onLoading = {
                                isLoading = true
                            },
                            onSuccess = { state ->
                                if (imageSize == DpSize.Zero) {
                                    isLoading = false
                                    imageSize = with(density) {
                                        state.painter.intrinsicSize.toDpSize()
                                    }
                                }
                            },
                            url = url
                        )
                    }

                    else -> {
                        Column(
                            content = {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp),
                                    color = colorResource(id = R.color.color_secondary_text_highlight),
                                    text = stringResource(id = R.string.title_ingredients)
                                )
                                Adapter(
                                    contentPadding = 0.dp,
                                    itemOrientation = ItemOrientation.Vertical,
                                    item = { _, ingredient ->
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 24.dp),
                                            color = MaterialTheme.colorScheme.onBackground,
                                            text = ingredient
                                        )
                                    },
                                    items = ingredients
                                )
                            }
                        )
                    }
                }
            }
        )
    }

}

@PreviewLightDark
@Composable
private fun DetailHeaderContentPrev() {
    DetailHeaderContent(
        drinkType = DrinkType.Alcoholic,
        glass = "Highball glass",
        ingredients = listOf(),
        name = "Mojito",
        url = "https://www.thecocktaildb.com/api/json/v1/1/random.php"
    )
}