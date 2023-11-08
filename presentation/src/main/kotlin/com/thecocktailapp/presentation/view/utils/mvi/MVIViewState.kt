package com.thecocktailapp.presentation.view.utils.mvi

import com.thecocktailapp.presentation.view.vo.DrinkVO

sealed class CommonViewState : HomeViewState, CocktailViewState, KotlinViewState {
    object Idle : CommonViewState()
    data class SetUpView(val data: Any? = null) : CommonViewState()
}

sealed interface HomeViewState {
    sealed class Navigate: HomeViewState {
        object ToComposeModule: Navigate()
        object ToKotlinModule: Navigate()
    }
}

sealed interface KotlinViewState

sealed interface CocktailViewState {
    data class ShowError(val idMessage: Int): CocktailViewState
    object ShowProgressDialog: CocktailViewState
    data class SetDailyCocktail(val drink: DrinkVO): CocktailViewState
}
