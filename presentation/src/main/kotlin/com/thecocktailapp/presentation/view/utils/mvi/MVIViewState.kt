package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonViewState : HomeViewState, KotlinViewState {
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