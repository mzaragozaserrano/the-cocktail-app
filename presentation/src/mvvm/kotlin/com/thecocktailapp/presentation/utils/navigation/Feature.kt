package com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.navigation

sealed class Feature(val route: String) {
    object Detail: Feature("detail")
    object Main: Feature("main")
    object Splash: Feature("splash")
}