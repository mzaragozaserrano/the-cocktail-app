package com.thecocktailapp.presentation.utils

sealed class CommonAction : KotlinAction, SplashAction, HomeAction,
    DetailDrinkAction {
    data object Idle : CommonAction()
    data class Init(val refresh: Boolean) : CommonAction()
}

sealed interface KotlinAction {
    sealed class Task : KotlinAction {
        data object CheckPreferencesToShowRandomDrink : Task()
    }
}

sealed interface SplashAction {
    sealed class Task : SplashAction {
        data object GetRandomDrink : Task()
    }
    sealed class TaskForNavigate : SplashAction {
        data object ToDrinkDetail : TaskForNavigate()
        data object ToMain : TaskForNavigate()
    }
}

sealed interface HomeAction

sealed interface DetailDrinkAction {
    sealed class Task : DetailDrinkAction {
        data class GetDrinkById(val id: Int) : Task()
    }

}