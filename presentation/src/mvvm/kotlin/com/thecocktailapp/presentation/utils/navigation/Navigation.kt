package com.thecocktailapp.presentation.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.main.MainScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.utils.extensions.composable

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
    navController: NavHostController,
) {
    navigation(
        startDestination = NavCommand.App(feature = Feature.Main).route,
        route = Feature.Main.route
    ) {
        composable(navItem = NavCommand.App(feature = Feature.Main)) {
            MainScreen(modifier = modifier)
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
        composable(route = NavCommand.App(feature = Feature.Detail).route) {
            DetailScreen(modifier = modifier, navController = navController)
        }
        composable(navItem = NavCommand.App(feature = Feature.Splash)) {
            SplashScreen(
                modifier = modifier,
                onSeeClicked = { id ->
                    navController.navigate(
                        route = NavCommand.Content(feature = Feature.Detail)
                            .createRoute(drinkId = id)
                    )
                },
                onCancelClicked = {
                    navController.navigate(
                        builder = {
                            popUpTo(route = Feature.Splash.route) {
                                inclusive = false
                            }
                        },
                        route = NavCommand.App(feature = Feature.Main).route
                    )
                }
            )
        }
    }
}