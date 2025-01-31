package com.thecocktailapp.presentation.screens.details

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.cards.RoundedCard
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
    url: String,
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
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineLarge,
            text = name,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.padding(end = 16.dp, start = 16.dp, top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {
            drinkType?.let { type ->
                WavyLabel(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .wrapContentWidth(),
                    buttonBackgroundColor = colorResource(id = type.buttonBackgroundColorId),
                    iconBackgroundColor = colorResource(id = type.iconBackgroundColorId),
                    iconId = type.iconId,
                    iconTint = colorResource(id = type.iconTintId),
                    textColor = colorResource(id = type.textColorId),
                    text = stringResource(id = type.labelId),
                    textStyle = MaterialTheme.typography.labelMedium
                )
            }
            if (glass.isNotEmpty()) {
                WavyLabel(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .wrapContentWidth(),
                    buttonBackgroundColor = MaterialTheme.colorScheme.inverseSurface,
                    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                    iconId = R.drawable.ic_cocktail,
                    iconTint = MaterialTheme.colorScheme.onTertiaryContainer,
                    textColor = MaterialTheme.colorScheme.inverseOnSurface,
                    text = glass,
                    textStyle = MaterialTheme.typography.labelMedium
                )
            }
        }

        RoundedCard(
            modifier = Modifier
                .conditional(condition = imageSize != DpSize.Zero) {
                    size(width = imageSize.width, height = imageSize.height)
                }
                .padding(all = 24.dp)
                .graphicsLayer {
                    cameraDistance = 16 * this.density
                    rotationY = animatedRotation
                    scaleX = if (animatedRotation > 90f) -1f else 1f
                },
            backgroundColor = MaterialTheme.colorScheme.primary,
            cornerRadius = 16.dp,
            shadowElevation = 2.dp,
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
                            modifier = Modifier.padding(all = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
                            content = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.headlineSmall,
                                    text = stringResource(id = R.string.title_ingredients)
                                )
                                Adapter(
                                    contentPadding = 0.dp,
                                    itemOrientation = ItemOrientation.Vertical,
                                    item = { _, ingredient ->
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            style = MaterialTheme.typography.bodyLarge,
                                            text = ingredient
                                        )
                                    },
                                    items = ingredients
                                )
                            }
                        )
                    }
                }
            },
            onCardClicked = {
                isFlipped = isFlipped.not()
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