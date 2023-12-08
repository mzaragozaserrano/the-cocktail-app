package com.thecocktailapp.presentation.vo

import com.mzaragozaserrano.presentation.vo.AppDrawerItemInfo
import com.thecocktailapp.ui.R

sealed class MenuItem {
    object CloseSession : MenuItem()
    object FavoriteScreen : MenuItem()
    object HomeScreen : MenuItem()
}

fun createMenuList(): List<AppDrawerItemInfo<MenuItem>> = listOf(
    AppDrawerItemInfo(
        drawerOption = MenuItem.HomeScreen,
        iconId = R.drawable.ic_cocktail,
        titleId = R.string.menu_item_home
    ),
    AppDrawerItemInfo(
        drawerOption = MenuItem.FavoriteScreen,
        iconId = R.drawable.ic_favorite,
        titleId = R.string.menu_item_favorites
    ),
    AppDrawerItemInfo(
        drawerOption = MenuItem.CloseSession,
        iconId = R.drawable.ic_close_session,
        titleId = R.string.menu_item_close_session
    )
)