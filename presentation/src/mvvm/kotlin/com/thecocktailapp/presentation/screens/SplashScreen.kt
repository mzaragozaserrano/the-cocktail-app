package com.thecocktailapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mzaragozaserrano.presentation.compose.components.buttons.PushedButton
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeBoldText
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraSmallMediumText
import com.thecocktailapp.presentation.alerts.ErrorAlert
import com.thecocktailapp.presentation.alerts.LottieProgressDialog
import com.thecocktailapp.presentation.images.UrlImage
import com.thecocktailapp.presentation.texts.BlinkingText
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.ui.R

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onSeeClicked: (String) -> Unit,
    onCancelClicked: () -> Unit,
) {

    val state by viewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var urlImage by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f)
        ) {
            HeaderContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background),
                urlImage = urlImage
            )
            InfoContent(
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
                ErrorAlert(idMessage = error.idMessage) {
                    viewModel.onExecuteGetRandomDrink()
                }
            }

            is SplashViewModel.SplashUiState.Idle -> {}
            is SplashViewModel.SplashUiState.Loading -> {
                LottieProgressDialog()
            }

            is SplashViewModel.SplashUiState.Success -> {
                val drink = (state as SplashViewModel.SplashUiState.Success).drink
                name = drink.name
                urlImage = (state as SplashViewModel.SplashUiState.Success).drink.urlImage
                ButtonsContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                    drink = drink,
                    onSeeClicked = onSeeClicked,
                    onCancelClicked = onCancelClicked
                )
            }
        }
    }
}

@Composable
private fun HeaderContent(modifier: Modifier = Modifier, urlImage: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(bottomEnd = 48.dp)
    ) {
        UrlImage(
            modifier = Modifier
                .padding(all = 24.dp)
                .aspectRatio(1f),
            cornerRadius = 8.dp,
            url = urlImage
        )
    }
}

@Composable
private fun InfoContent(modifier: Modifier = Modifier, name: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(topStart = 48.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 48.dp, horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(space = 6.dp)
        ) {
            if (name.isNotEmpty()) {
                ExtraSmallMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.color_secondary_text_highlight),
                    text = name
                )
                ExtraLargeBoldText(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onBackground,
                    text = stringResource(id = R.string.first_title_splash_fragment).uppercase()
                )
                BlinkingText(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.color_text_highlight),
                    text = stringResource(id = R.string.second_title_splash_fragment).uppercase()
                )
                ExtraLargeBoldText(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onBackground,
                    text = stringResource(id = R.string.third_title_splash_fragment).uppercase()
                )
            }
        }
    }
}

@Composable
private fun ButtonsContent(
    modifier: Modifier = Modifier,
    drink: DrinkVO,
    onSeeClicked: (String) -> Unit,
    onCancelClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        PushedButton(
            modifier = Modifier.fillMaxWidth(),
            buttonBackgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            textId = R.string.see_button
        ) {
            onSeeClicked(drink.id)
        }
        ExtraSmallMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCancelClicked() },
            color = MaterialTheme.colorScheme.primary,
            text = stringResource(id = R.string.cancel_button),
            textAlign = TextAlign.Center
        )
    }
}