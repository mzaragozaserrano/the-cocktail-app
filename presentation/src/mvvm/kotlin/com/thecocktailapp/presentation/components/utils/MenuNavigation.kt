package com.thecocktailapp.presentation.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzs.core.domain.utils.generic.DateUtils
import com.mzs.core.domain.utils.generic.ddMMyyyy_HHmm
import com.mzs.core.presentation.components.view.MenuDrawerContent
import com.mzs.core.presentation.utils.extensions.conditional
import com.mzs.core.presentation.utils.generic.emptyText
import com.mzs.core.presentation.vo.MenuItemVO
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.utils.HOME_MENU_BUTTON
import com.thecocktailapp.presentation.utils.HOME_TOOLBAR
import com.thecocktailapp.presentation.utils.MENU_NAVIGATION_ITEM
import com.thecocktailapp.presentation.utils.extensions.getGreetingText
import com.thecocktailapp.presentation.vo.MenuItemTheCocktailAppVO
import com.thecocktailapp.presentation.vo.getMenuOptions
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNavigation(
    modifier: Modifier = Modifier,
    dateUtils: DateUtils = koinInject(),
    drawerState: DrawerState,
    loading: Boolean,
    onMenuItemClicked: (MenuItemVO) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    val date = dateUtils.getCurrentDate(formatOut = ddMMyyyy_HHmm)

    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            MenuDrawerContent(
                date = date,
                dateTextColor = MaterialTheme.colorScheme.primary,
                initScreen = MenuItemTheCocktailAppVO.HomeScreen,
                drawerState = drawerState,
                greetingTextColor = MaterialTheme.colorScheme.primary,
                greetingTextId = date.getGreetingText(),
                iconTint = MaterialTheme.colorScheme.onBackground,
                screens = getMenuOptions(),
                testTag = MENU_NAVIGATION_ITEM,
                textColor = MaterialTheme.colorScheme.onBackground,
                onMenuItemClicked = onMenuItemClicked
            )
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.4F)
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    modifier = Modifier.testTag(tag = HOME_TOOLBAR),
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                    navigationIcon = {
                        Icon(
                            modifier = Modifier
                                .testTag(tag = HOME_MENU_BUTTON)
                                .conditional(!loading) { clip(shape = CircleShape).clickable { coroutineScope.launch { drawerState.open() } } }
                                .padding(all = 8.dp),
                            imageVector = Icons.Rounded.Menu,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = emptyText
                        )
                    },
                    title = {
                        Text(
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.headlineSmall,
                            text = stringResource(id = R.string.toolbar_title_home)
                        )
                    },
                )
            },
            content = { paddingValues ->
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            content = content
                        )
                    }
                )
            }
        )
    }
}