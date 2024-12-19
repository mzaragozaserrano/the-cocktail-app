package com.thecocktailapp.presentation.utils

sealed class CommonIntent : KotlinIntent, SplashIntent, HomeIntent,
    DetailDrinkIntent {
    object Idle : CommonIntent()
    data class Init(val refresh: Boolean = false) : CommonIntent()
}

sealed interface KotlinIntent {
    object ShowRandomDrink: KotlinIntent
}

sealed interface SplashIntent  {
    object GetRandomDrink: SplashIntent
    object GoToDrinkDetail: SplashIntent
    object GoToMain: SplashIntent
}

sealed interface HomeIntent

sealed interface DetailDrinkIntent {
    data class GetDrinkById(val id: Int?) : DetailDrinkIntent
}