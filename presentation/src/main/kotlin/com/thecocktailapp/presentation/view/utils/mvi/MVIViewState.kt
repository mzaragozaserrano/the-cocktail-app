package com.thecocktailapp.presentation.view.utils.mvi

import com.thecocktailapp.presentation.view.vo.DrinkVO

sealed class CommonViewState : HomeViewState, KotlinViewState, SplashViewState, CocktailViewState {
    object Idle : CommonViewState()
    data class Initialized(val data: Any? = null) : CommonViewState()
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
}

sealed interface SplashViewState {
    sealed class Navigate: SplashViewState {
        object ToDrinkDetail: Navigate()
        object ToMain: Navigate()
    }
    data class SetDailyDrink(val drink: DrinkVO): SplashViewState
    data class ShowError(val idMessage: Int): SplashViewState
    object ShowProgressDialog: SplashViewState
}