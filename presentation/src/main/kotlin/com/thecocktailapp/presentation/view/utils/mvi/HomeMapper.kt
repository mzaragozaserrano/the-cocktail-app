package com.thecocktailapp.presentation.view.utils.mvi

import com.mzaragozaserrano.presentation.view.utils.mvi.CommonViewState
import com.mzaragozaserrano.presentation.view.utils.mvi.HomeViewState

fun HomeIntent.mapToAction(): HomeAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
    }

fun HomeResult.mapToState(): HomeViewState =
    when (this) {
        is CommonResult.Idle -> CommonViewState.Idle
        is HomeResult.Init -> CommonViewState.Initialized()
    }