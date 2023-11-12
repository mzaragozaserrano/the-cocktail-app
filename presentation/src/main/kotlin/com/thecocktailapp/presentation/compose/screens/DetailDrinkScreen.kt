package com.thecocktailapp.presentation.compose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thecocktailapp.presentation.compose.viewmodels.DetailDrinkViewModel

@Composable
fun DetailDrinkScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailDrinkViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    if (state.id != 0) {
        Text(text = state.id.toString())
    }

}