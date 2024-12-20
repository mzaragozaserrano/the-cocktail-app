package com.thecocktailapp.presentation.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thecocktailapp.presentation.R

@Composable
fun DetailIngredientsContent(
    ingredients: List<String>,
    showInstructions: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        AnimatedVisibility(
            enter = slideInHorizontally(
                animationSpec = tween(durationMillis = 2000 / (ingredients.size + 1)),
                initialOffsetX = { it }
            ),
            visible = showInstructions,
            label = ""
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = colorResource(id = R.color.color_secondary_text_highlight),
                text = stringResource(id = R.string.title_ingredients)
            )
        }
        ingredients.forEachIndexed { index, ingredient ->
            AnimatedVisibility(
                enter = slideInHorizontally(
                    animationSpec = tween(durationMillis = (2000 / (ingredients.size + 1) * (index + 1))),
                    initialOffsetX = { it }
                ),
                visible = showInstructions,
                label = ""
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    text = ingredient
                )
            }
        }
    }
}