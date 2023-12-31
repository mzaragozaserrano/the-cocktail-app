package com.thecocktailapp.presentation.screens.main

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mzaragozaserrano.presentation.compose.components.utils.MenuDrawerContent
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.vo.MenuItem
import com.thecocktailapp.presentation.vo.createMenuList
import com.thecocktailapp.ui.R

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            MenuDrawerContent(
                defaultPick = MenuItem.HomeScreen,
                drawerState = drawerState,
                greetingTextColor = MaterialTheme.colorScheme.secondary,
                greetingTextId = R.string.menu_greeting,
                iconTint = MaterialTheme.colorScheme.primary,
                menuItems = createMenuList(),
                textColor = MaterialTheme.colorScheme.primary,
                timeTextColor = MaterialTheme.colorScheme.secondary,
                timeTextId = R.string.menu_time
            ) { onMenuItemClicked ->
                when (onMenuItemClicked) {
                    is MenuItem.CloseSession -> {

                    }

                    is MenuItem.FavoriteScreen -> {

                    }

                    is MenuItem.HomeScreen -> {

                    }
                }
            }
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4F)
    ) {
        HomeScreen(modifier = modifier, drawerState = drawerState, navController = navController)
    }

}