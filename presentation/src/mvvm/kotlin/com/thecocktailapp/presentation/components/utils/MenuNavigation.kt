package com.thecocktailapp.presentation.components.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.mzs.core.presentation.components.view.MenuDrawerContent
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.utils.HOME_MENU_BUTTON
import com.thecocktailapp.presentation.utils.HOME_TOOLBAR
import com.thecocktailapp.presentation.utils.MENU_NAVIGATION_ITEM
import com.thecocktailapp.presentation.utils.extensions.getGreetingText
import com.thecocktailapp.presentation.vo.MenuItemVO
import com.thecocktailapp.presentation.vo.getMenuOptions
import com.thecocktailapp.presentation.vo.toPair
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNavigation(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    onHomeRefreshed: () -> Unit,
    content: @Composable () -> Unit,
) {

//    val date = getCurrentDate(sdfComplete)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            MenuDrawerContent(
                date = "12/03/1995 12:30",
                dateTextColor = MaterialTheme.colorScheme.secondary,
                initScreen = MenuItemVO.HomeScreen.toPair(),
                drawerState = drawerState,
                greetingTextColor = MaterialTheme.colorScheme.secondary,
                greetingTextId = "12/03/1995 12:30".getGreetingText(),
                iconTint = MaterialTheme.colorScheme.primary,
                screens = getMenuOptions(),
                testTag = MENU_NAVIGATION_ITEM,
                textColor = MaterialTheme.colorScheme.primary
            ) { onMenuItemClicked ->

            }
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4F)
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    modifier = Modifier.testTag(tag = HOME_TOOLBAR),
                    title = {
                        Text(
                            color = MaterialTheme.colorScheme.onSurface,
                            text = stringResource(id = R.string.toolbar_title_home)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.testTag(tag = HOME_MENU_BUTTON),
                            onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = "MenuButton"
                            )
                        }
                    },
                )
            }
        ) { paddingValues ->
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    content()
                }
            }
        }
    }
}