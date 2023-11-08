package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonAction : HomeAction, KotlinAction, CocktailAction {
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

sealed interface CocktailAction {
    sealed class Task: CocktailAction {
        object GetRandomCocktail: Task()
    }
}