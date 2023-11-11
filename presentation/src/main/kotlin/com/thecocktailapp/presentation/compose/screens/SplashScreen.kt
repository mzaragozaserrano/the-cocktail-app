package com.thecocktailapp.presentation.compose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.thecocktailapp.presentation.compose.viewmodels.SplashViewModel

@Composable
fun SplashScreen(modifier: Modifier = Modifier, viewModel: SplashViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    when (state) {
        is SplashViewModel.SplashUiState.Error -> {
            Text(stringResource((state as SplashViewModel.SplashUiState.Error).error.idMessage))
        }

        is SplashViewModel.SplashUiState.Idle -> {}
        is SplashViewModel.SplashUiState.Loading -> {
            Text("... CARGANDO ...")
        }

        is SplashViewModel.SplashUiState.Success -> {
            Text((state as SplashViewModel.SplashUiState.Success).drink.name)
        }
    }

}