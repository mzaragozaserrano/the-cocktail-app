package com.thecocktailapp.presentation.compose.utils.navigation

sealed class AppNavigation {
    object Compose : AppNavigation()
    object Kotlin : AppNavigation()
}