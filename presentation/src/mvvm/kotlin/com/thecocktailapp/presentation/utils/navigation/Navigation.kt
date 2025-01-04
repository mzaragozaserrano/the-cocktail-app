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
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.screens.splash.SplashScreen
import com.thecocktailapp.presentation.vo.DrinkVO
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Any,
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        screenNavigation<Splash> {
            SplashScreen(
                modifier = modifier,
                onSeeClicked = { drink ->
                    navController.navigate(
                        route = Detail(
                            data = drink,
                            isFromSplash = true
                        )
                    )
                },
                onCancelClicked = { navController.navigate(route = Home) }
            )
        }
        screenNavigation<Home> {
            HomeScreen(
                modifier = modifier,
                drawerState = drawerState,
                navController = navController
            )
        }

        /*composable(navItem = NavCommand.App(feature = Feature.Favorites)) {
            FavoritesScreen(modifier = modifier, navController = navController)
        }*/
        screenNavigationWithParameters<Detail, DrinkVO> { parameter ->
            DetailScreen(
                modifier = modifier,
                drink = parameter.data,
                onIconClicked = {
                    if (parameter.isFromSplash) {
                        navController.navigate(route = Home)
                    } else {
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