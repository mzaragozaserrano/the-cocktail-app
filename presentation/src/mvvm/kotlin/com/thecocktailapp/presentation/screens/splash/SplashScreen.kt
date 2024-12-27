package com.thecocktailapp.presentation.screens.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mzs.core.presentation.utils.extensions.conditional
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.viewmodels.SplashViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onSeeClicked: (String) -> Unit,
    onCancelClicked: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()

    BackHandler { onCancelClicked() }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .conditional(condition = uiState.success == null) {
                        weight(weight = 1f)
                    }
                    .background(color = MaterialTheme.colorScheme.background)
                    .clip(shape = RoundedCornerShape(bottomEnd = 48.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                uiState.success?.let { success ->
                    SplashHeaderContent(urlImage = success.drink.urlImage)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .clip(shape = RoundedCornerShape(topStart = 48.dp))
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                uiState.success?.let { success ->
                    if (success.drink.name.isNotEmpty()) {
                        SplashInfoContent(
                            name = success.drink.name,
                            onSeeClicked = {
                                onSeeClicked(success.drink.id)
                            },
                            onCancelClicked = onCancelClicked
                        )
                    }
                }

            }
        }
        if (uiState.loading) {
            ProgressDialog()
        }
        uiState.error?.let { error ->
            ErrorDialog(
                buttonText = stringResource(id = R.string.retry_button),
                messageText = stringResource(id = error.messageId),
                onButtonClicked = { viewModel.onRetryExecuteGetRandomDrink() }
            )
        }
    }

}