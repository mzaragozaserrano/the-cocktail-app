package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonResult : HomeResult {
    object Idle : CommonResult()
}

sealed interface HomeResult {
    object Init : HomeResult

}