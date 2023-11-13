package com.thecocktailapp.presentation.compose.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mzaragozaserrano.presentation.compose.components.cards.RoundedCard
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeBlackText
import com.mzaragozaserrano.presentation.compose.components.texts.LargeBoldText
import com.mzaragozaserrano.presentation.compose.components.texts.SmallMediumText
import com.thecocktailapp.presentation.compose.alerts.ErrorAlert
import com.thecocktailapp.presentation.compose.alerts.LottieProgressDialog
import com.thecocktailapp.presentation.compose.images.UrlImage
import com.thecocktailapp.presentation.compose.utils.navigation.Feature
import com.thecocktailapp.presentation.compose.utils.navigation.NavCommand
import com.thecocktailapp.presentation.compose.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.view.vo.DrinkVO
import com.thecocktailapp.ui.R

@Composable
fun DetailDrinkScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailDrinkViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    var ingredients by remember { mutableStateOf(listOf<String>()) }
    var showInstructions by remember { mutableStateOf(false) }

    BackHandler(
        enabled = true,
        onBack = {
            navController.navigate(
                route = NavCommand.Home(feature = Feature.App).route,
                builder = {
                    popUpTo(Feature.Splash.route) {
                        inclusive = false
                    }
                }
            )
        }
    )

    LaunchedEffect(ingredients) {
        if (ingredients.isNotEmpty()) {
            showInstructions = true
        }
    }

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is DetailDrinkViewModel.DetailDrinkUiState.Error -> {
                val error = (state as DetailDrinkViewModel.DetailDrinkUiState.Error).error
                ErrorAlert(error) {
                    viewModel.onExecuteGetDrinkById()
                }
            }

            is DetailDrinkViewModel.DetailDrinkUiState.Idle -> {}
            is DetailDrinkViewModel.DetailDrinkUiState.Loading -> {
                LottieProgressDialog()
            }

            is DetailDrinkViewModel.DetailDrinkUiState.Success -> {
                val drink = (state as DetailDrinkViewModel.DetailDrinkUiState.Success).drink
                ingredients = drink.ingredients
                RoundedCard(
                    modifier = Modifier.padding(vertical = 48.dp, horizontal = 24.dp),
                    backgroundColor = MaterialTheme.colorScheme.background
                ) {
                    HeaderContent(drink = drink)
                    IngredientsContent(
                        ingredients = ingredients,
                        showInstructions = showInstructions
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderContent(drink: DrinkVO) {
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
            .aspectRatio(1f),
        cornerRadius = 8.dp,
        url = drink.urlImage
    )
}

@Composable
private fun IngredientsContent(
    ingredients: List<String>,
    showInstructions: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        AnimatedVisibility(
            visible = showInstructions,
            enter =
            slideInHorizontally(
                animationSpec = tween(2000 / (ingredients.size + 1)),
                initialOffsetX = { it }
            ), label = ""
        ) {
            LargeBoldText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = colorResource(id = R.color.color_secondary_text_highlight),
                text = stringResource(R.string.title_ingredients)
            )
        }
        ingredients.forEachIndexed { index, ingredient ->
            AnimatedVisibility(
                visible = showInstructions,
                enter =
                slideInHorizontally(
                    animationSpec = tween((2000 / (ingredients.size + 1) * (index + 1))),
                    initialOffsetX = { it }
                ), label = ""
            ) {
                SmallMediumText(
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