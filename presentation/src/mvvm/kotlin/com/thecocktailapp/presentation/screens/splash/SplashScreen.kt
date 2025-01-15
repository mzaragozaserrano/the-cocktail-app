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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mzs.core.presentation.utils.extensions.conditional
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel(),
    onGoToDetail: (DrinkVO) -> Unit,
    onGoToHome: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(onBack = { onGoToHome() })

    LaunchedEffect(key1 = Unit) { viewModel.onExecuteGetRandomDrink() }

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
                    .background(color = MaterialTheme.colorScheme.primary),
                content = {
                    uiState.success?.let { success ->
                        SplashHeaderContent(urlImage = success.drink.urlImage)
                    }
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .clip(shape = RoundedCornerShape(topStart = 48.dp))
                    .background(color = MaterialTheme.colorScheme.background),
                content = {
                    uiState.success?.let { success ->
                        if (success.drink.name.isNotEmpty()) {
                            SplashInfoContent(
                                name = success.drink.name,
                                onSeeClicked = {
                                    onGoToDetail(success.drink)
                                },
                                onCancelClicked = onGoToHome
                            )
                        }
                    }

                }
            )
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