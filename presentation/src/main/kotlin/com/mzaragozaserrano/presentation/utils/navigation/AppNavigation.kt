package com.mzaragozaserrano.presentation.utils.navigation

sealed class AppNavigation {
    object Compose : AppNavigation()
    object Kotlin : AppNavigation()
}