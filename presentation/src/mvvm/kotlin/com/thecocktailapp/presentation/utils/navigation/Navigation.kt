package com.thecocktailapp.presentation.utils.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mzs.core.presentation.navigation.ParameterScreen
import com.mzs.core.presentation.navigation.screenNavigation
import com.mzs.core.presentation.navigation.screenNavigationWithParameters
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.favorites.FavoritesScreen
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.utils.extensions.composable
import com.thecocktailapp.presentation.vo.DrinkVO
import kotlinx.serialization.Serializable

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
        screenNavigation<SplashScreen> {
            SplashScreen(
                modifier = modifier,
                onSeeClicked = { id ->
                    /*navController.navigate(
                        route = NavCommand.Content(feature = Feature.Detail)
                            .createRoute(drinkId = id)
                    )*/
                },
                onCancelClicked = {
                    /*navController.navigate(
                        builder = {
                            popUpTo(route = Feature.Splash.route) {
                                inclusive = false
                            }
                        },
                        route = NavCommand.App(feature = Feature.Home).route
                    )*/
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
        screenNavigationWithParameters<Detail, DrinkVO> { parameter ->
            DetailScreen(
                modifier = modifier,
                drink = parameter.drink.data,
                onIconClicked = {

                }
            )
        }
        composable(navItem = NavCommand.Content(feature = Feature.Detail)) {

        }
    }

}

@Serializable
data object SplashScreen

@Serializable
data class Detail(val drink: Drink)

@Serializable
data class Drink(override val data: DrinkVO): ParameterScreen<DrinkVO>