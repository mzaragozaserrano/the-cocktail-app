package com.thecocktailapp.presentation.utils

import com.thecocktailapp.presentation.vo.DrinkVO

sealed class CommonViewState : KotlinViewState, SplashViewState, HomeViewState,
    DetailDrinkViewState {
    object Idle : CommonViewState()
    data class Initialized(val data: Any? = null) : CommonViewState()
}

sealed interface KotlinViewState {
    sealed class Navigate : KotlinViewState {
        data object ToHomeFragment : Navigate()
        data object ToSplashFragment : Navigate()

    }
}

sealed interface HomeViewState {
    data class ShowError(val idMessage: Int) : HomeViewState
    data object ShowProgressDialog : HomeViewState
}

sealed interface SplashViewState {
    sealed class Navigate : SplashViewState {
        data class ToDrinkDetail(val id: Int) : Navigate()
        data object ToHomeFragment : Navigate()
    }

    data class ShowView(val drink: DrinkVO) : SplashViewState
    data class ShowError(val idMessage: Int) : SplashViewState
    data object ShowProgressDialog : SplashViewState
}

sealed interface DetailDrinkViewState {
    data class ShowView(val drink: DrinkVO) : DetailDrinkViewState

    data class ShowError(val idMessage: Int) : DetailDrinkViewState
    data object ShowProgressDialog : DetailDrinkViewState
}