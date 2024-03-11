package com.thecocktailapp.presentation.screens.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.Navigation
import com.thecocktailapp.presentation.viewmodels.app.AppViewModel

@Composable
fun AppScreen(modifier: Modifier = Modifier, viewModel: AppViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()

    Navigation(
        modifier = modifier,
        navController = navController,
        startDestination = if (state.showRandomDrink) Feature.Splash.route else Feature.Main.route
    )

}