package com.thecocktailapp.presentation.view.utils.mvi

import com.thecocktailapp.presentation.view.utils.transform

fun KotlinIntent.mapToAction(): KotlinAction =
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
            CommonViewState.SetUpView()
        }

        is CocktailResult.Task.Loading -> {
            CocktailViewState.ShowProgressDialog
        }

        is CocktailResult.Task.Success -> {
            when (task) {
                is CocktailTask.RandomCocktailGotten -> {
                    CocktailViewState.SetDailyCocktail(drink = task.drink.transform())
                }
            }
        }
    }