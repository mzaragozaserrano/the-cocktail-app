package com.thecocktailapp.presentation.view.utils.mvi

import com.thecocktailapp.presentation.view.vo.HomeVO

fun HomeIntent.mapToAction(): HomeAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
        is HomeIntent.NavigateToComposeModule -> HomeAction.TaskForNavigate.ToComposeModule
        is HomeIntent.NavigateToKotlinModule -> HomeAction.TaskForNavigate.ToKotlinModule
    }

fun HomeResult.mapToState(): HomeViewState =
    when (this) {
        is CommonResult.Idle -> CommonViewState.Idle
        is HomeResult.Init -> {
            CommonViewState.SetUpView(
                HomeVO(
                    buttonCompose = buttonCompose,
                    buttonKotlin = buttonKotlin
                )
            )
        }

        is HomeResult.Task.Success.GoToComposeModule -> {
            HomeViewState.Navigate.ToComposeModule
        }

        is HomeResult.Task.Success.GoToKotlinModule -> {
            HomeViewState.Navigate.ToKotlinModule
        }
    }