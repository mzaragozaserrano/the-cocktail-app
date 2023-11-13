package com.thecocktailapp.presentation.view.utils.mvi

import com.thecocktailapp.presentation.view.utils.transform

fun DetailDrinkIntent.mapToAction(): DetailDrinkAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
        is DetailDrinkIntent.GetDrinkById -> DetailDrinkAction.Task.GetDrinkById(id = id)
    }

fun DetailDrinkResult.mapToState(): DetailDrinkViewState =
    when (this) {
        is CommonResult.Idle -> {
            CommonViewState.Idle
        }
        is DetailDrinkResult.Init -> {
            if (drink != null) {
                CommonViewState.Idle
            } else {
                CommonViewState.Initialized()
            }
        }
        is DetailDrinkResult.Task.Error -> {
            DetailDrinkViewState.ShowError(idMessage = error.idMessage)
        }
        is DetailDrinkResult.Task.Loading -> {
            DetailDrinkViewState.ShowProgressDialog
        }
        is DetailDrinkResult.Task.Success -> {
            when (task) {
                is DetailDrinkTask.DrinkGotten -> {
                    DetailDrinkViewState.SetDrink(drink = task.drink.transform())
                }
            }
        }
    }