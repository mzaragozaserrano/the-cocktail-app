package com.thecocktailapp.presentation.utils.mvi

import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonResult
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonViewState
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeResult
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeViewState

fun HomeIntent.mapToAction(): HomeAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
    }

fun HomeResult.mapToState(): HomeViewState =
    when (this) {
        is CommonResult.Idle -> {
            CommonViewState.Idle
        }

        is HomeResult.Task.Error -> {
            HomeViewState.ShowError(idMessage = error.messageId)
        }

        is HomeResult.Init -> {
            CommonViewState.Initialized()
        }

        is HomeResult.Task.Loading -> {
            HomeViewState.ShowProgressDialog
        }
    }