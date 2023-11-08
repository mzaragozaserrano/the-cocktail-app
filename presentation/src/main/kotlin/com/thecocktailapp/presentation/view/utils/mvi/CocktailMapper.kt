package com.thecocktailapp.presentation.view.utils.mvi

fun CocktailIntent.mapToAction(): CocktailAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
    }

fun CocktailResult.mapToState(): CocktailViewState =
    when (this) {
        is CommonResult.Idle -> {
            CommonViewState.Idle
        }

        is CocktailResult.Task.Error -> {
            CocktailViewState.ShowError(idMessage = error.idMessage)
        }

        is CocktailResult.Init -> {
            CommonViewState.Initialized()
        }

        is CocktailResult.Task.Loading -> {
            CocktailViewState.ShowProgressDialog
        }
    }