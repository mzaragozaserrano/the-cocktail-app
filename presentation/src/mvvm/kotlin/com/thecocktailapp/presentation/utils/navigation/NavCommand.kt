package com.thecocktailapp.presentation.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    internal val navRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList(),
) {

    class Content(feature: Feature) :
        NavCommand(feature = feature, navArgs = listOf(NavArg.DrinkId)) {
        fun createRoute(drinkId: Int) = "$navRoute/${feature.route}/$drinkId"
    }
    class Home(feature: Feature) : NavCommand(feature = feature)

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
    object DrinkId : NavArg("drinkId", NavType.IntType)
}