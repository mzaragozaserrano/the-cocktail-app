package com.thecocktailapp.presentation.utils.mvi

sealed class CommonAction : KotlinAction, SplashAction, HomeAction,
    DetailDrinkAction {
    object Idle : CommonAction()
    data class Init(val refresh: Boolean) : CommonAction()
}

sealed interface KotlinAction {
    sealed class Task : KotlinAction {
        object CheckPreferencesToShowRandomDrink : Task()
    }
}

sealed interface SplashAction {
    sealed class Task : SplashAction {
        object GetRandomDrink : Task()
    }
    sealed class TaskForNavigate : SplashAction {
        object ToDrinkDetail : TaskForNavigate()
        object ToMain : TaskForNavigate()
    }
}

sealed interface HomeAction

sealed interface DetailDrinkAction {
    sealed class Task : DetailDrinkAction {
        data class GetDrinkById(val id: Int) : Task()
    }

}