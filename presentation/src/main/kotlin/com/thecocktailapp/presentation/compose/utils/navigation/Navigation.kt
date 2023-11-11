package com.thecocktailapp.presentation.compose.utils.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.thecocktailapp.presentation.compose.screens.SplashScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        theCocktailAppNav(modifier = modifier, navController = navController)
        splashNav(modifier = modifier)
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
            Text(text = "Esto es el main")
        }
    }
}

private fun NavGraphBuilder.splashNav(modifier: Modifier = Modifier) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.Splash).route,
        route = Feature.Splash.route
    ) {
        composable(navItem = NavCommand.ContentType(Feature.Splash)) {
            SplashScreen(modifier = modifier)
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