package com.thecocktailapp.presentation.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.thecocktailapp.presentation.screens.CocktailScreen
import com.thecocktailapp.presentation.screens.DetailDrinkScreen
import com.thecocktailapp.presentation.screens.SplashScreen
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
    navController: NavController,
) {
    navigation(
        startDestination = NavCommand.Home(Feature.App).route,
        route = Feature.App.route
    ) {
        composable(navItem = NavCommand.Home(Feature.App)) {
            CocktailScreen()
        }
        composable(navItem = NavCommand.Content(Feature.Detail)) {
            DetailDrinkScreen(modifier = modifier, navController = navController)
        }
    }
}

private fun NavGraphBuilder.splashNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    navigation(
        startDestination = NavCommand.Home(feature = Feature.Splash).route,
        route = Feature.Splash.route
    ) {
        composable(navItem = NavCommand.Home(feature = Feature.Splash)) {
            SplashScreen(
                modifier = modifier,
                onSeeClicked = { id ->
                    navController.navigate(
                        route = NavCommand.Content(Feature.Detail).createRoute(drinkId = id.toInt())
                    )
                },
                onCancelClicked = {
                    navController.navigate(
                        route = NavCommand.Home(Feature.App).route,
                        builder = {
                            popUpTo(Feature.Splash.route) {
                                inclusive = false
                            }
                        }
                    )
                }
            )
        }
    }
}