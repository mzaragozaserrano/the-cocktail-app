package com.thecocktailapp.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.presentation.R

sealed class MenuItemVO(@DrawableRes val iconId: Int, @StringRes val titleId: Int) {
    data object CloseSession : MenuItemVO(
        iconId = R.drawable.ic_close_session,
        titleId = R.string.menu_item_close_session
    )

    data object FavoriteScreen : MenuItemVO(
        iconId = R.drawable.ic_favorite,
        titleId = R.string.menu_item_favorites
    )

    data object HomeScreen : MenuItemVO(
        iconId = R.drawable.ic_cocktail,
        titleId = R.string.menu_item_home
    )
}

fun getMenuOptions(): List<Pair<Int, Int>> =
    listOf(
        MenuItemVO.HomeScreen.toPair(),
        MenuItemVO.FavoriteScreen.toPair(),
        MenuItemVO.CloseSession.toPair()
    )

fun MenuItemVO.toPair(): Pair<Int, Int> = Pair(iconId, titleId)