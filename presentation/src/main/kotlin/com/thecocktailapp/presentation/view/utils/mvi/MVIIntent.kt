package com.thecocktailapp.presentation.view.utils.mvi

sealed class CommonIntent : HomeIntent, KotlinIntent, CocktailIntent {
    object Idle : CommonIntent()
    data class Init(val refresh: Boolean = false) : CommonIntent()
}

sealed interface HomeIntent {
    object NavigateToComposeModule: HomeIntent
    object NavigateToKotlinModule: HomeIntent
}

sealed interface KotlinIntent

sealed interface CocktailIntent {
    object GetRandomCocktail: CocktailIntent
}