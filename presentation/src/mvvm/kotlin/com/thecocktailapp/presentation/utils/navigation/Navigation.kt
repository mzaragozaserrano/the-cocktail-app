package com.thecocktailapp.presentation.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mzs.core.presentation.navigation.ParameterScreen
import com.mzs.core.presentation.navigation.screenNavigation
import com.mzs.core.presentation.navigation.screenNavigationWithParameters
import com.mzs.core.presentation.utils.generic.nullInt
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.favorites.FavoritesScreen
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.utils.RESULT_FROM_DETAIL
import com.thecocktailapp.presentation.utils.RESULT_FROM_FAVORITES
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.MenuItemTheCocktailAppVO
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    startDestination: Any,
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        screenNavigationWithParameters<Detail, DrinkVO> { parameter ->
            DetailScreen(
                modifier = modifier,
                drinkId = parameter.data.id,
                isFavorite = parameter.data.isFavorite,
                onGoBack = { drinkId ->
                    if (parameter.isFromSplash) {
                        navController.navigate(route = Home)
                    } else {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(key = RESULT_FROM_DETAIL, value = drinkId)
                        navController.popBackStack()
                    }
                }
            )
        }
        screenNavigation<Favorites> { entry ->
            FavoritesScreen(
                modifier = modifier,
                drinkId = entry.savedStateHandle.get<Int>(RESULT_FROM_DETAIL) ?: nullInt,
                onGoToDetail = { drink ->
                    navController.navigate(
                        route = Detail(
                            data = drink,
                            isFromSplash = false
                        )
                    )
                },
                onGoBack = { forceRefresh ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(key = RESULT_FROM_FAVORITES, value = forceRefresh)
                    navController.popBackStack()
                }
            )
            entry.savedStateHandle.remove<Int>(RESULT_FROM_DETAIL)
        }
        screenNavigation<Home> { entry ->
            HomeScreen(
                modifier = modifier,
                drinkId = entry.savedStateHandle.get<Int>(RESULT_FROM_DETAIL) ?: nullInt,
                forceRefresh = entry.savedStateHandle.get<Boolean>(RESULT_FROM_FAVORITES) ?: false,
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
                            navController.navigate(route = Favorites)
                        }

                        is MenuItemTheCocktailAppVO.HomeScreen -> {
                            //Refrescar
                        }
                    }
                }
            )
            entry.savedStateHandle.remove<Int>(RESULT_FROM_DETAIL)
            entry.savedStateHandle.remove<Boolean>(RESULT_FROM_FAVORITES)
        }
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
    }

}

@Serializable
data class Detail(override val data: DrinkVO, val isFromSplash: Boolean) :
    ParameterScreen<DrinkVO>

@Serializable
data object Favorites

@Serializable
data object Home

@Serializable
data object Splash