package com.thecocktailapp.presentation.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mzaragozaserrano.presentation.compose.components.cards.RoundedCard
import com.thecocktailapp.presentation.components.ErrorDialog
import com.thecocktailapp.presentation.components.ProgressDialog
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
                route = NavCommand.App(feature = Feature.Main).route
            )
        }
    )

    LaunchedEffect(key1 = ingredients) {
        if (ingredients.isNotEmpty()) {
            showInstructions = true
        }
    }

    LazyColumn(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            when (state) {
                is DetailDrinkViewModel.DetailDrinkUiState.Error -> {
                    val error = (state as DetailDrinkViewModel.DetailDrinkUiState.Error).error
                    ErrorDialog(
                        buttonTextId = R.string.retry_button,
                        messageTextId = error.messageId
                    ) {
                        viewModel.onExecuteGetDrinkById()
                    }
                }

                is DetailDrinkViewModel.DetailDrinkUiState.Idle -> {}
                is DetailDrinkViewModel.DetailDrinkUiState.Loading -> {
                    ProgressDialog()
                }

                is DetailDrinkViewModel.DetailDrinkUiState.Success -> {
                    val drink = (state as DetailDrinkViewModel.DetailDrinkUiState.Success).drink
                    ingredients = drink.ingredients
                    RoundedCard(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 48.dp),
                        backgroundColor = MaterialTheme.colorScheme.background
                    ) {
                        DetailHeaderContent(
                            drinkType = drink.drinkType,
                            glass = drink.glass,
                            name = drink.name.uppercase(),
                            url = drink.urlImage
                        )
                        DetailIngredientsContent(
                            ingredients = ingredients,
                            showInstructions = showInstructions
                        )
                        DetailInstructionsContent(
                            modifier = Modifier.fillMaxWidth(),
                            instructions = drink.instructions
                        )
                    }
                }
            }
        }
    }

}