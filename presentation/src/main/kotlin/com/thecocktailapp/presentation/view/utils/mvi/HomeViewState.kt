package com.mzaragozaserrano.presentation.view.utils.mvi

sealed class CommonViewState : HomeViewState {
    object Idle : CommonViewState()
    data class Initialized(val data: Any? = null, val refresh: Boolean = false) : CommonViewState()
}

sealed interface HomeViewState {
}