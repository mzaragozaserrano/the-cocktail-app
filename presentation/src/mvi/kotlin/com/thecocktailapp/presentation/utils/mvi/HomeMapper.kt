package com.thecocktailapp.presentation.utils.mvi

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