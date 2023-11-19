package com.thecocktailapp.presentation.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mzaragozaserrano.presentation.compose.components.alerts.ErrorAlert
import com.mzaragozaserrano.presentation.compose.components.cards.RoundedCard
import com.mzaragozaserrano.presentation.compose.components.utils.LottieProgressDialog
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.ui.R

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailDrinkViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    var ingredients by remember { mutableStateOf(value = listOf<String>()) }
    var showInstructions by remember { mutableStateOf(value = false) }

    BackHandler(
        enabled = true,
        onBack = {
            navController.navigate(
                builder = {
                    popUpTo(Feature.Splash.route) {
                        inclusive = false
                    }
                },
                route = NavCommand.Home(feature = Feature.App).route
            )
        }
    )

    LaunchedEffect(ingredients) {
        if (ingredients.isNotEmpty()) {
            showInstructions = true
        }
    }

    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is DetailDrinkViewModel.DetailDrinkUiState.Error -> {
                val error = (state as DetailDrinkViewModel.DetailDrinkUiState.Error).error
                ErrorAlert(
                    alertBackgroundColor = MaterialTheme.colorScheme.background,
                    buttonBackgroundColor = MaterialTheme.colorScheme.errorContainer,
                    buttonTextColor = MaterialTheme.colorScheme.background,
                    buttonTextId = R.string.retry_button,
                    messageTextColor = colorResource(id = R.color.color_on_background),
                    messageTextId = error.idMessage,
                    titleTextColor = colorResource(id = R.color.color_error_container),
                    titleTextId = R.string.title_error
                ) {
                    viewModel.onExecuteGetDrinkById()
                }
            }

            is DetailDrinkViewModel.DetailDrinkUiState.Idle -> {}
            is DetailDrinkViewModel.DetailDrinkUiState.Loading -> {
                LottieProgressDialog(animation = R.raw.loading)
            }

            is DetailDrinkViewModel.DetailDrinkUiState.Success -> {
                val drink = (state as DetailDrinkViewModel.DetailDrinkUiState.Success).drink
                ingredients = drink.ingredients
                RoundedCard(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 48.dp),
                    backgroundColor = MaterialTheme.colorScheme.background
                ) {
                    DetailHeaderContent(drink = drink)
                    DetailIngredientsContent(
                        ingredients = ingredients,
                        showInstructions = showInstructions
                    )
                }
            }
        }
    }

}