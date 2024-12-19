package com.thecocktailapp.presentation.components.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.R
import com.thecocktailapp.core.presentation.compose.components.texts.ExtraSmallLightText
import com.thecocktailapp.core.presentation.compose.components.texts.NormalBoldText
import com.mzs.core.presentation.vo.FakeItem
import com.mzs.core.presentation.vo.MenuDrawerItemVO
import kotlinx.coroutines.launch

@Composable
fun <T> MenuDrawerContent(
    modifier: Modifier = Modifier,
    modifierItem: Modifier = Modifier,
    date: String,
    dateTextColor: Color,
    defaultPick: T,
    drawerState: DrawerState,
    greetingTextColor: Color,
    @StringRes greetingTextId: Int,
    iconTint: Color,
    menuItems: List<MenuDrawerItemVO<T>>,
    testTag: String = "",
    textColor: Color,
    onClick: (T) -> Unit,
) {
    var currentPick by remember { mutableStateOf(defaultPick) }
    val coroutineScope = rememberCoroutineScope()
    ModalDrawerSheet(modifier = modifier) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                NormalBoldText(
                    modifier = Modifier.padding(end = 16.dp, start = 16.dp, top = 16.dp),
                    color = greetingTextColor,
                    text = stringResource(id = greetingTextId)
                )
                ExtraSmallLightText(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = dateTextColor,
                    text = date
                )
                Recycler(
                    modifier = Modifier.padding(top = 16.dp),
                    list = menuItems
                ) { item ->
                    MenuDrawerItem(
                        modifier = modifierItem,
                        iconTint = iconTint,
                        isSecondItem = menuItems.indexOf(item) == 1,
                        item = item,
                        testTag = testTag,
                        textColor = textColor
                    ) { navOption ->
                        if (currentPick != navOption || currentPick == defaultPick) {
                            currentPick = navOption
                            onClick(navOption)
                        }
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MenuDrawerContentPrev() {
    MenuDrawerContent(
        date = stringResource(id = R.string.core_hello_world),
        dateTextColor = MaterialTheme.colorScheme.errorContainer,
        defaultPick = FakeItem.HOME,
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        greetingTextColor = MaterialTheme.colorScheme.error,
        greetingTextId = R.string.core_message_alert_error,
        iconTint = MaterialTheme.colorScheme.onPrimary,
        menuItems = listOf(),
        textColor = MaterialTheme.colorScheme.onPrimary
    ) {
        //Here will go the action when clicking on the button
    }
}