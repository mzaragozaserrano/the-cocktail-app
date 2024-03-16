package com.thecocktailapp.presentation.screens.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.utils.navigation.Navigation
import com.thecocktailapp.presentation.viewmodels.app.ComposeViewModel

@Composable
fun ComposeScreen(modifier: Modifier = Modifier, viewModel: ComposeViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()

    Navigation(
        modifier = modifier,
        navController = navController,
        startDestination = if (state.showRandomDrink) {
            NavCommand.App(feature = Feature.Splash).route
        } else {
            NavCommand.App(feature = Feature.Home).route
        }
    )

}