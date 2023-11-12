package com.thecocktailapp.presentation.compose.utils.navigation

sealed class Feature(val route: String) {
    object App: Feature("app")
    object Detail: Feature("detail")
    object Splash: Feature("splash")
}