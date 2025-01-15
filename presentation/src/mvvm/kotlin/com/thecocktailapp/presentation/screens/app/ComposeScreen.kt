package com.thecocktailapp.presentation.screens.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.thecocktailapp.presentation.utils.navigation.Home
import com.thecocktailapp.presentation.utils.navigation.Navigation
import com.thecocktailapp.presentation.utils.navigation.Splash
import com.thecocktailapp.presentation.viewmodels.ComposeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposeScreen(modifier: Modifier = Modifier, viewModel: ComposeViewModel = koinViewModel()) {

    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { viewModel.onExecuteShowRandomDrink() }

    uiState.success?.let { success ->
        Navigation(
            modifier = modifier,
            navController = navController,
            startDestination = if (success.showRandomDrink) {
                Splash
            } else {
                Home
            }
        )
    }

}