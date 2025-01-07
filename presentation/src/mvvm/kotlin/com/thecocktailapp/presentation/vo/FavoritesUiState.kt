package com.thecocktailapp.presentation.vo

data class FavoritesUiState(
    val error: ErrorVO? = null,
    val loading: Boolean = false,
    val success: FavoritesSuccess? = null,
)

data class FavoritesSuccess(val drinks: List<DrinkVO>, val initDrinks: List<DrinkVO>)