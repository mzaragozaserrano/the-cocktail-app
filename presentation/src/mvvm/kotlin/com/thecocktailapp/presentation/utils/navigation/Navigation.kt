package com.thecocktailapp.presentation.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mzs.core.presentation.navigation.ParameterScreen
import com.mzs.core.presentation.navigation.screenNavigation
import com.mzs.core.presentation.navigation.screenNavigationWithParameters
import com.mzs.core.presentation.utils.generic.nullInt
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.utils.RESULT_HOME_FROM_DETAIL
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.MenuItemTheCocktailAppVO
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Any,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        screenNavigation<Splash> {
            SplashScreen(
                modifier = modifier,
                onGoToDetail = { drink ->
                    navController.navigate(
                        route = Detail(
                            data = drink,
                            isFromSplash = true
                        )
                    )
                },
                onGoToHome = { navController.navigate(route = Home) }
            )
        }
        screenNavigation<Home> { entry ->
            HomeScreen(
                modifier = modifier,
                drinkId = entry.savedStateHandle.get<Int>(RESULT_HOME_FROM_DETAIL) ?: nullInt,
                onGoToDetail = { drink ->
                    navController.navigate(
                        route = Detail(
                            data = drink,
                            isFromSplash = false
                        )
                    )
                },
                onMenuItemClicked = { item ->
                    when (item as MenuItemTheCocktailAppVO) {/*
                        is MenuItemTheCocktailAppVO.CloseSession -> {

                        }*/

                        is MenuItemTheCocktailAppVO.FavoriteScreen -> {

                        }

                        is MenuItemTheCocktailAppVO.HomeScreen -> {

                        }
                    }
                }
            )
        }

        /*composable(navItem = NavCommand.App(feature = Feature.Favorites)) {
            FavoritesScreen(modifier = modifier, navController = navController)
        }*/
        screenNavigationWithParameters<Detail, DrinkVO> { parameter ->
            DetailScreen(
                modifier = modifier,
                drinkId = parameter.data.id,
                isFavorite = parameter.data.isFavorite,
                onBackPressed = { drinkId ->
                    if (parameter.isFromSplash) {
                        navController.navigate(route = Home)
                    } else {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(key = RESULT_HOME_FROM_DETAIL, value = drinkId)
                        navController.popBackStack()
                    }
                }
            )
        }
    }

}

@Serializable
data object Home

@Serializable
data object Splash

@Serializable
data class Detail(override val data: DrinkVO, val isFromSplash: Boolean) :
    ParameterScreen<DrinkVO>