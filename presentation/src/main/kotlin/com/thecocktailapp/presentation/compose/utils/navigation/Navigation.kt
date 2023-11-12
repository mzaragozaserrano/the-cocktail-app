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
import com.thecocktailapp.presentation.compose.screens.DetailDrinkScreen
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
        splashNav(modifier = modifier, navController = navController)
        theCocktailAppNav(modifier = modifier, navController = navController)
        cocktailDetailNav(modifier = modifier, navController = navController)
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

private fun NavGraphBuilder.splashNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    navigation(
        startDestination = NavCommand.ContentType(feature = Feature.Splash).route,
        route = Feature.Splash.route
    ) {
        composable(navItem = NavCommand.ContentType(feature = Feature.Splash)) {
            SplashScreen(
                modifier = modifier,
                onSeeClicked = { id ->
                    navController.navigate(
                        route = NavCommand.ContentDetail(Feature.Detail).createRoute(drinkId = id.toInt())
                    )
                },
                onCancelClicked = {
                    navController.navigate(
                        route = NavCommand.ContentType(feature = Feature.App).route,
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

private fun NavGraphBuilder.cocktailDetailNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    navigation(
        startDestination = NavCommand.ContentDetail(feature = Feature.Detail).route,
        route = Feature.Detail.route
    ) {
        composable(navItem = NavCommand.ContentDetail(feature = Feature.Detail)) {
            DetailDrinkScreen()
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