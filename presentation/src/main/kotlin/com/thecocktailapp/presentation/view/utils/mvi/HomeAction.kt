package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonAction : HomeAction {
    object Idle : CommonAction()
    data class Init(val refresh: Boolean) : CommonAction()
}

sealed interface HomeAction {
}