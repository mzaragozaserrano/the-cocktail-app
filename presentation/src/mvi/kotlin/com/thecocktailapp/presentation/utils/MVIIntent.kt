package com.thecocktailapp.presentation.utils

sealed class CommonIntent : KotlinIntent, SplashIntent, HomeIntent,
    DetailDrinkIntent {
    data object Idle : CommonIntent()
    data class Init(val refresh: Boolean = false) : CommonIntent()
}

sealed interface KotlinIntent {
    data object ShowRandomDrink : KotlinIntent
}

sealed interface SplashIntent  {
    data object GetRandomDrink : SplashIntent
    data object GoToDrinkDetail : SplashIntent
    data object GoToMain : SplashIntent
}

sealed interface HomeIntent

sealed interface DetailDrinkIntent {
    data class GetDrinkById(val id: Int?) : DetailDrinkIntent
}