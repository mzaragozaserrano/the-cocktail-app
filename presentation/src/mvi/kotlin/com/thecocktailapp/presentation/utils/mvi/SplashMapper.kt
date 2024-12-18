package com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi

import com.thecocktailapp.presentation.utils.transform

fun SplashIntent.mapToAction(): SplashAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
        is SplashIntent.GetRandomDrink -> SplashAction.Task.GetRandomDrink
        is SplashIntent.GoToDrinkDetail -> SplashAction.TaskForNavigate.ToDrinkDetail
        is SplashIntent.GoToMain -> SplashAction.TaskForNavigate.ToMain
    }

fun SplashResult.mapToState(): SplashViewState =
    when (this) {
        is CommonResult.Idle -> {
            CommonViewState.Idle
        }

        is SplashResult.Init -> {
            if (drink != null) {
                CommonViewState.Idle
            } else {
                CommonViewState.Initialized()
            }
        }

        is SplashResult.Task.Error -> {
            SplashViewState.ShowError(idMessage = error.messageId)
        }

        is SplashResult.Task.Loading -> {
            SplashViewState.ShowProgressDialog
        }

        is SplashResult.Task.Success -> {
            when (task) {
                is SplashTask.NavigateToDrinkDetail -> {
                    SplashViewState.Navigate.ToDrinkDetail(id = task.id)
                }

                is SplashTask.NavigateToHomeFragment -> {
                    SplashViewState.Navigate.ToHomeFragment
                }

                is SplashTask.DrinkGotten -> {
                    SplashViewState.SetDrink(drink = task.drink.transform())
                }
            }
        }
    }