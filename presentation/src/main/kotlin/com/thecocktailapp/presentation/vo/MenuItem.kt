package com.thecocktailapp.presentation.vo

import com.thecocktailapp.core.presentation.vo.MenuDrawerItemVO
import com.thecocktailapp.presentation.R

sealed class MenuItem {
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
    )
)