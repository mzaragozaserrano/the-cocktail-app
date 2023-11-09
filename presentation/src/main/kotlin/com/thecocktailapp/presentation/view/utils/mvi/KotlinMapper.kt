package com.thecocktailapp.presentation.view.utils.mvi

fun KotlinIntent.mapToAction(): KotlinAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
        is KotlinIntent.ShowRandomDrink -> KotlinAction.Task.CheckPreferencesToShowRandomDrink
    }

fun KotlinResult.mapToState(): KotlinViewState =
    when (this) {
        is CommonResult.Idle -> CommonViewState.Idle
        is KotlinResult.Init -> {
            CommonViewState.Initialized()
        }

        is KotlinResult.Task.Success -> {
            when (task) {
                is KotlinTask.NavigateToCocktailFragment -> {
                    KotlinViewState.Navigate.ToCocktailFragment
                }

                is KotlinTask.NavigateToSplashFragment -> {
                    KotlinViewState.Navigate.ToSplashFragment
                }
            }
        }
    }