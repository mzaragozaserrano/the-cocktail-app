package com.thecocktailapp.presentation.view.utils.mvi

fun CocktailIntent.mapToAction(): CocktailAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
        is CocktailIntent.GetRandomCocktail -> CocktailAction.Task.GetRandomCocktail
    }

fun KotlinResult.mapToState(): KotlinViewState =
    when (this) {
        is CommonResult.Idle -> CommonViewState.Idle
        is KotlinResult.Init -> {
            CommonViewState.SetUpView()
        }
    }