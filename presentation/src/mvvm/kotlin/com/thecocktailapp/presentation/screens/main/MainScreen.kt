package com.thecocktailapp.presentation.screens.main

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.thecocktailapp.core.domain.utils.getCurrentDate
import com.thecocktailapp.core.domain.utils.sdfComplete
import com.thecocktailapp.presentation.screens.home.HomeScreen
import com.thecocktailapp.presentation.utils.extensions.getGreetingText
import com.thecocktailapp.presentation.vo.MenuItem
import com.thecocktailapp.presentation.vo.createMenuList
import presentation.components.utils.MenuDrawerContent

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val date = getCurrentDate(sdfComplete)
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