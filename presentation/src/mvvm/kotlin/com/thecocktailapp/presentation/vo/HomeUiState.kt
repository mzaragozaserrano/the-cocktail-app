package com.thecocktailapp.presentation.vo

data class HomeUiState(
    val error: ErrorVO? = null,
    val loading: Boolean = false,
    val success: HomeSuccess? = null,
    val drinkType: DrinkType = DrinkType.Alcoholic,
)

data class HomeSuccess(val drinks: List<DrinkVO>)