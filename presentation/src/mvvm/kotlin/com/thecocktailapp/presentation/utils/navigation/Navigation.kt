package com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.extensions.composable
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.main.MainScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.utils.navigation.Feature

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
        splashNav(modifier = modifier, navController = navController)
        theCocktailAppNav(modifier = modifier, navController = navController)
    }

}

private fun NavGraphBuilder.theCocktailAppNav(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    navigation(
        startDestination = NavCommand.App(feature = Feature.Home).route,
        route = Feature.Home.route
    ) {

        composable(navItem = NavCommand.App(feature = Feature.Home)) {
            MainScreen(modifier = modifier, navController = navController)
        }

        composable(navItem = NavCommand.Content(feature = Feature.Detail)) {
            DetailScreen(modifier = modifier, navController = navController)
        }

    }

}

private fun NavGraphBuilder.splashNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    navigation(
        startDestination = NavCommand.App(feature = Feature.Splash).route,
        route = Feature.Splash.route
    ) {

        composable(navItem = NavCommand.App(feature = Feature.Splash)) {
            SplashScreen(
                modifier = modifier,
                onSeeClicked = { id ->
                    navController.navigate(
                        route = NavCommand.Content(feature = Feature.Detail)
                            .createRoute(drinkId = id.toInt())
                    )
                },
                onCancelClicked = {
                    navController.navigate(
                        builder = {
                            popUpTo(route = Feature.Splash.route) {
                                inclusive = false
                            }
                        },
                        route = NavCommand.App(feature = Feature.Home).route
                    )
                }
            )
        }

    }

}