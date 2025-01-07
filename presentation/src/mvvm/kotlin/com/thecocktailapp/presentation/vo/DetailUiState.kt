package com.thecocktailapp.presentation.vo

data class DetailUiState(
    val error: ErrorVO? = null,
    val loading: Boolean = false,
    val success: DetailSuccess? = null,
)

data class DetailSuccess(
    val drink: DrinkVO,
    val initIsFavorite: Boolean = false,
    val isFavorite: Boolean = false,
)