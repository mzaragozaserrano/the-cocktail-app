package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonIntent : HomeIntent {
    object Idle : CommonIntent()
    data class Init(val refresh: Boolean = false) : CommonIntent()
}

sealed interface HomeIntent {

}