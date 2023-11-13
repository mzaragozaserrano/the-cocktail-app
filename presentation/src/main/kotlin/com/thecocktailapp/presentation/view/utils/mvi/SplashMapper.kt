package com.thecocktailapp.presentation.view.utils.mvi

import com.thecocktailapp.presentation.view.utils.transform

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
            SplashViewState.ShowError(idMessage = error.idMessage)
        }

        is SplashResult.Task.Loading -> {
            SplashViewState.ShowProgressDialog
        }

        is SplashResult.Task.Success -> {
            when (task) {
                is SplashTask.NavigateToDrinkDetail -> {
                    SplashViewState.Navigate.ToDrinkDetail(id = task.id)
                }

                is SplashTask.NavigateToCocktailFragment -> {
                    SplashViewState.Navigate.ToCocktailFragment
                }

                is SplashTask.DrinkGotten -> {
                    SplashViewState.SetDrink(drink = task.drink.transform())
                }
            }
        }
    }