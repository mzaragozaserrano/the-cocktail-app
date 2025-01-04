package com.thecocktailapp.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mzs.core.presentation.vo.MenuItemVO
import com.thecocktailapp.presentation.R

sealed class MenuItemTheCocktailAppVO(
    @DrawableRes override val iconId: Int,
    @StringRes override val titleId: Int
) : MenuItemVO {
    data object CloseSession : MenuItemTheCocktailAppVO(
        iconId = R.drawable.ic_close_session,
        titleId = R.string.menu_item_close_session
    )

    data object FavoriteScreen : MenuItemTheCocktailAppVO(
        iconId = R.drawable.ic_favorite,
        titleId = R.string.menu_item_favorites
    )

    data object HomeScreen : MenuItemTheCocktailAppVO(
        iconId = R.drawable.ic_cocktail,
        titleId = R.string.menu_item_home
    )
}

fun getMenuOptions(): List<MenuItemTheCocktailAppVO> =
    listOf(
        MenuItemTheCocktailAppVO.HomeScreen,
        MenuItemTheCocktailAppVO.FavoriteScreen,
        MenuItemTheCocktailAppVO.CloseSession
    )