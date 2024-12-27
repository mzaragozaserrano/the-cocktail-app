package com.thecocktailapp.presentation.vo

data class SplashUiState(
    val loading: Boolean = false,
    val error: ErrorVO? = null,
    val success: SplashSuccess? = null,
)

data class SplashSuccess(val drink: DrinkVO)