package com.thecocktailapp.presentation.screens.main

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.extensions.getGreetingText
import com.thecocktailapp.core.domain.utils.getCurrentDate
import com.thecocktailapp.core.domain.utils.sdfComplete
import com.thecocktailapp.core.presentation.compose.components.utils.MenuDrawerContent
import com.thecocktailapp.presentation.screens.details.DetailScreen
import com.thecocktailapp.presentation.screens.favorites.FavoritesScreen
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.utils.extensions.composable
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.vo.MenuItem
import com.thecocktailapp.presentation.vo.createMenuList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val date = getCurrentDate(sdfComplete)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            MenuDrawerContent(
                date = date,
                dateTextColor = MaterialTheme.colorScheme.secondary,
                defaultPick = MenuItem.HomeScreen,
                drawerState = drawerState,
                greetingTextColor = MaterialTheme.colorScheme.secondary,
                greetingTextId = date.getGreetingText(),
                iconTint = MaterialTheme.colorScheme.primary,
                menuItems = createMenuList(),
                textColor = MaterialTheme.colorScheme.primary
            ) { onMenuItemClicked ->
                when (onMenuItemClicked) {
                    is MenuItem.FavoriteScreen -> {
                        coroutineScope.launch { drawerState.close() }
                        navController.navigate(NavCommand.App(feature = Feature.Favorites).route)
                    }

                    is MenuItem.HomeScreen -> {
                        coroutineScope.launch { drawerState.close() }
                        navController.navigate(NavCommand.App(feature = Feature.Home).route)
                    }
                }
            }
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4F)
    ) {
        NavHost(
            navController = navController,
            startDestination = NavCommand.App(feature = Feature.Home).route
        ) {
            composable(navItem = NavCommand.Content(feature = Feature.Detail)) {
                DetailScreen(modifier = modifier, navController = navController)
            }
            composable(navItem = NavCommand.App(feature = Feature.Home)) {
                HomeScreen(
                    modifier = modifier,
                    drawerState = drawerState,
                    navController = navController
                )
            }
            composable(navItem = NavCommand.App(feature = Feature.Favorites)) {
                FavoritesScreen(
                    modifier = modifier,
                    drawerState = drawerState,
                    navController = navController
                )
            }
        }
    }
}