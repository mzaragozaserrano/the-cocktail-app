package com.thecocktailapp.presentation.view.utils.mvi

fun KotlinIntent.mapToAction(): KotlinAction =
    when (this) {
        is CommonIntent.Idle -> CommonAction.Idle
        is CommonIntent.Init -> CommonAction.Init(refresh)
    }

fun KotlinResult.mapToState(): KotlinViewState =
    when (this) {
        is CommonResult.Idle -> CommonViewState.Idle
        is KotlinResult.Init -> {
            CommonViewState.Initialized()
        }
    }