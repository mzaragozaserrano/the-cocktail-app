package com.thecocktailapp.presentation.vo

import com.mzaragozaserrano.presentation.vo.MenuDrawerItemVO
import com.thecocktailapp.ui.R

sealed class MenuItem {
    object CloseSession : MenuItem()
    object FavoriteScreen : MenuItem()
    object HomeScreen : MenuItem()
}

fun createMenuList(): List<MenuDrawerItemVO<MenuItem>> = listOf(
    MenuDrawerItemVO(
        drawerOption = MenuItem.HomeScreen,
        iconId = R.drawable.ic_cocktail,
        titleId = R.string.menu_item_home
    ),
    MenuDrawerItemVO(
        drawerOption = MenuItem.FavoriteScreen,
        iconId = R.drawable.ic_favorite,
        titleId = R.string.menu_item_favorites
    ),
    MenuDrawerItemVO(
        drawerOption = MenuItem.CloseSession,
        iconId = R.drawable.ic_close_session,
        titleId = R.string.menu_item_close_session
    )
)