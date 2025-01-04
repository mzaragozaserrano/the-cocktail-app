package com.thecocktailapp.presentation.vo

data class SplashUiState(
    val error: ErrorVO? = null,
    val loading: Boolean = false,
    val success: SplashSuccess? = null,
)

data class SplashSuccess(val drink: DrinkVO)