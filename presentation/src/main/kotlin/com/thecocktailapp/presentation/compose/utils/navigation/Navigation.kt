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