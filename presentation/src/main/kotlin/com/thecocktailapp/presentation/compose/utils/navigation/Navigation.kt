package com.thecocktailapp.presentation.compose.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.thecocktailapp.presentation.compose.screens.ComposeScreen
import com.mzaragozaserrano.presentation.compose.screens.HomeScreen
import com.thecocktailapp.presentation.compose.screens.KotlinScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Feature.App.route
    ) {
        theCocktailAppNav(modifier = modifier, navController = navController)
        composeNav(modifier = modifier)
        kotlinNav(modifier = modifier)
    }
}

private fun NavGraphBuilder.theCocktailAppNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.App).route,
        route = Feature.App.route
    ) {
        composable(navItem = NavCommand.ContentType(Feature.App)) {
            HomeScreen(modifier = modifier) { navigation ->
                when (navigation) {
                    is AppNavigation.Compose -> {
                        navController.navigate(Feature.Compose.route)
                    }

                    is AppNavigation.Kotlin -> {
                        navController.navigate(Feature.Kotlin.route)
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.composeNav(modifier: Modifier = Modifier) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.Compose).route,
        route = Feature.Compose.route
    ) {
        composable(navItem = NavCommand.ContentType(Feature.Compose)) {
            ComposeScreen(modifier = modifier)
        }
    }
}

private fun NavGraphBuilder.kotlinNav(modifier: Modifier = Modifier) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.Kotlin).route,
        route = Feature.Kotlin.route
    ) {
        composable(navItem = NavCommand.ContentType(Feature.Kotlin)) {
            KotlinScreen(modifier = modifier)
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}