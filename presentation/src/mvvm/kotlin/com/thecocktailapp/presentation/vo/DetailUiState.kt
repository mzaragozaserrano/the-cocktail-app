package com.thecocktailapp.presentation.vo

data class DetailUiState(
    val loading: Boolean = false,
    val error: ErrorVO? = null,
    val success: DetailSuccess? = null,
)

data class DetailSuccess(val drink: DrinkVO, val isFavorite: Boolean)