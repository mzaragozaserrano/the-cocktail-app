package com.thecocktailapp.presentation.utils.navigation

sealed class Feature(val route: String) {
    object Detail : Feature("detail")
    object Home : Feature("home")
    object Splash : Feature("splash")
}