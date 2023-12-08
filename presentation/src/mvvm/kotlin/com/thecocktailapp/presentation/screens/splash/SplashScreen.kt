package com.thecocktailapp.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thecocktailapp.presentation.components.DualActionButton
import com.thecocktailapp.presentation.components.ErrorDialog
import com.thecocktailapp.presentation.components.ProgressDialog
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import com.thecocktailapp.ui.R

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onSeeClicked: (String) -> Unit,
    onCancelClicked: () -> Unit,
) {

    val state by viewModel.state.collectAsState()
    var name by remember { mutableStateOf(value = "") }
    var urlImage by remember { mutableStateOf(value = "") }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f)
        ) {
            SplashHeaderContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background),
                urlImage = urlImage
            )
            SplashInfoContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f)
                    .background(color = MaterialTheme.colorScheme.primary),
                name = name
            )
        }
        when (state) {
            is SplashViewModel.SplashUiState.Error -> {
                val error = (state as SplashViewModel.SplashUiState.Error).error
                ErrorDialog(buttonTextId = R.string.retry_button, messageTextId = error.messageId) {
                    viewModel.onExecuteGetRandomDrink()
                }
            }

            is SplashViewModel.SplashUiState.Idle -> {}
            is SplashViewModel.SplashUiState.Loading -> {
                ProgressDialog()
            }

            is SplashViewModel.SplashUiState.Success -> {
                val drink = (state as SplashViewModel.SplashUiState.Success).drink
                name = drink.name
                urlImage = (state as SplashViewModel.SplashUiState.Success).drink.urlImage
                DualActionButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(bottom = 24.dp, end = 24.dp, start = 24.dp),
                    buttonBackgroundColor = MaterialTheme.colorScheme.primary,
                    buttonTextColor = MaterialTheme.colorScheme.onPrimary,
                    buttonTextId = R.string.see_button,
                    textId = R.string.retry_button,
                    onSeeClicked = {
                        onSeeClicked(drink.id)
                    },
                    onCancelClicked = onCancelClicked
                )
            }
        }
    }

}