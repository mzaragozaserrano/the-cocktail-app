package com.thecocktailapp.presentation.screens.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.thecocktailapp.presentation.utils.navigation.Home
import com.thecocktailapp.presentation.utils.navigation.Navigation
import com.thecocktailapp.presentation.utils.navigation.Splash
import com.thecocktailapp.presentation.viewmodels.ComposeViewModel

@Composable
fun ComposeScreen(modifier: Modifier = Modifier, viewModel: ComposeViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { viewModel.onExecuteShowRandomDrink() }

    Navigation(
        modifier = modifier,
        navController = navController,
        startDestination = if (uiState.showRandomDrink) {
            Splash
        } else {
            Home
        }
    )

}