package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonAction : HomeAction, KotlinAction, SplashAction, CocktailAction {
    object Idle : CommonAction()
    data class Init(val refresh: Boolean) : CommonAction()
}

sealed interface HomeAction {
    sealed class TaskForNavigate : HomeAction {
        object ToComposeModule : TaskForNavigate()
        object ToKotlinModule : TaskForNavigate()
    }
}

sealed interface KotlinAction

sealed interface SplashAction {
    sealed class Task : SplashAction {
        object GetRandomDrink : Task()
    }
    sealed class TaskForNavigate : SplashAction {
        object ToDrinkDetail : TaskForNavigate()
        object ToMain : TaskForNavigate()
    }
}

sealed interface CocktailAction