package com.thecocktailapp.presentation.utils.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.favorites.FavoritesScreen
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.utils.extensions.composable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
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
                        route = NavCommand.App(feature = Feature.Home).route
                    )
                }
            )
        }
        composable(navItem = NavCommand.App(feature = Feature.Home)) {
            HomeScreen(
                modifier = modifier,
                drawerState = drawerState,
                navController = navController
            )
        }

        composable(navItem = NavCommand.App(feature = Feature.Favorites)) {
            FavoritesScreen(modifier = modifier, navController = navController)
        }

        composable(navItem = NavCommand.Content(feature = Feature.Detail)) {
            DetailScreen(modifier = modifier, navController = navController)
        }
    }

}