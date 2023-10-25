package com.mzaragozaserrano.presentation.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.buttons.MinimalButton
import com.mzaragozaserrano.ui.R
import com.thecocktailapp.presentation.compose.utils.navigation.AppNavigation

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onButtonClicked: (AppNavigation) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(all = 16.dp)
    ) {
        MinimalButton(
            textId = R.string.button_hello_compose,
            iconId = R.drawable.ic_compose,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            onButtonClicked(AppNavigation.Compose)
        }
        MinimalButton(
            textId = R.string.button_hello_kotlin,
            iconId = R.drawable.ic_kotlin,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            onButtonClicked(AppNavigation.Kotlin)
        }
    }
}

@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(modifier = Modifier, onButtonClicked = {})
}
