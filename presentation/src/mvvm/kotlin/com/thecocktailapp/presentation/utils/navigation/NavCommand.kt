package com.thecocktailapp.presentation.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    internal val navRoute: String = "app",
    private val navArgs: List<NavArg> = emptyList(),
) {
    class App(feature: Feature) : NavCommand(feature = feature)

    class Content(feature: Feature) :
        NavCommand(feature = feature, navArgs = listOf(NavArg.DrinkId)) {
        fun createRoute(drinkId: String) = "$navRoute/${feature.route}/$drinkId"
    }

    val route = run {
        val argKeys = navArgs.map {
            "{${it.key}}"
        }
        listOf(navRoute, feature.route).plus(argKeys).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

sealed class NavArg(val key: String, val navType: NavType<*>) {
    object DrinkId : NavArg("drinkId", NavType.StringType)
}