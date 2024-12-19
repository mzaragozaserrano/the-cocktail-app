package com.thecocktailapp.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.presentation.R

sealed class MenuItemVO(@DrawableRes val iconId: Int, @StringRes val titleId: Int) {
    object CloseSession : MenuItemVO(
        iconId = R.drawable.ic_close_session,
        titleId = R.string.menu_item_close_session
    )

    object FavoriteScreen : MenuItemVO(
        iconId = R.drawable.ic_favorite,
        titleId = R.string.menu_item_home
    )

    object HomeScreen : MenuItemVO(
        iconId = R.drawable.ic_cocktail,
        titleId = R.string.menu_item_favorites
    )
}

fun getMenuOptions(): List<MenuItemVO> =
    listOf(MenuItemVO.HomeScreen, MenuItemVO.FavoriteScreen, MenuItemVO.CloseSession)