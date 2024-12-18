package com.thecocktailapp.presentation.utils.mvi

import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonResult
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonViewState
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkResult
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkTask
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkViewState
import com.thecocktailapp.presentation.utils.transform

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
            DetailDrinkViewState.ShowError(idMessage = error.messageId)
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