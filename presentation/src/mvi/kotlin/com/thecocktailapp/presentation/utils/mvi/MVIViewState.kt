package com.thecocktailapp.presentation.utils.mvi

import com.thecocktailapp.presentation.vo.DrinkVO

sealed class CommonViewState : KotlinViewState, SplashViewState, CocktailViewState,
    DetailDrinkViewState {
    object Idle : CommonViewState()
    data class Initialized(val data: Any? = null) : CommonViewState()
}

sealed interface KotlinViewState {
    sealed class Navigate : KotlinViewState {
        object ToCocktailFragment : Navigate()
        object ToSplashFragment : Navigate()

    }
}

sealed interface CocktailViewState {
    data class ShowError(val idMessage: Int) : CocktailViewState
    object ShowProgressDialog : CocktailViewState
}

sealed interface SplashViewState {
    sealed class Navigate : SplashViewState {
        data class ToDrinkDetail(val id: Int) : Navigate()
        object ToCocktailFragment : Navigate()
    }

    data class SetDrink(val drink: DrinkVO) : SplashViewState
    data class ShowError(val idMessage: Int) : SplashViewState
    object ShowProgressDialog : SplashViewState
}

sealed interface DetailDrinkViewState {
    data class SetDrink(val drink: DrinkVO) : DetailDrinkViewState

    data class ShowError(val idMessage: Int) : DetailDrinkViewState
    object ShowProgressDialog : DetailDrinkViewState
}