package com.thecocktailapp.presentation.compose.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList(),
) {

    class ContentType(feature: Feature) : NavCommand(feature)
    class ContentDetail(feature: Feature) : NavCommand(feature, "detail", listOf(NavArg.ItemId)) {
        fun createRoute(typeName: String) = "${feature.route}/$subRoute/$typeName"
    }

    val route = run {
        val argKeys = navArgs.map {
            "{${it.key}}"
        }
        listOf(feature.route, subRoute).plus(argKeys).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

sealed class NavArg(val key: String, val navType: NavType<*>) {
    object ItemId : NavArg("itemId", NavType.IntType)
}