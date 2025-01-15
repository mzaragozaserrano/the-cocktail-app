package com.thecocktailapp.presentation.vo

data class ComposeUiState(val success: ComposeSuccess? = null)

data class ComposeSuccess(val showRandomDrink: Boolean)