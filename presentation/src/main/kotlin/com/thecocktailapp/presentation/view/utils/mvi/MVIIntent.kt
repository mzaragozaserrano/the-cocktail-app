package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonIntent : HomeIntent, KotlinIntent, SplashIntent, CocktailIntent, DetailDrinkIntent {
    object Idle : CommonIntent()
    data class Init(val refresh: Boolean = false) : CommonIntent()
}

sealed interface HomeIntent {
    object GoToComposeModule: HomeIntent
    object GoToKotlinModule: HomeIntent
}

sealed interface KotlinIntent {
    object ShowRandomDrink: KotlinIntent
}

sealed interface SplashIntent  {
    object GetRandomDrink: SplashIntent
    object GoToDrinkDetail: SplashIntent
    object GoToMain: SplashIntent
}

sealed interface CocktailIntent

sealed interface DetailDrinkIntent {
    data class GetDrinkById(val id: Int): DetailDrinkIntent
}