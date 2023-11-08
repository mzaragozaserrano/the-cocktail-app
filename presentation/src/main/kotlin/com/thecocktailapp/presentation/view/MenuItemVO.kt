package com.thecocktailapp.presentation.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.ui.R

sealed class MenuItem(@DrawableRes val icon: Int, @StringRes val name: Int) {
    object CloseSession : MenuItem(
        icon = R.drawable.ic_close_session,
        name = R.string.menu_item_close_session
    )
}

fun getMenuOptions(): List<MenuItem> =
    listOf(MenuItem.CloseSession)