package com.thecocktailapp.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.presentation.R

sealed class MenuItemVO(@DrawableRes val icon: Int, @StringRes val name: Int) {
    object CloseSession : MenuItemVO(
        icon = R.drawable.ic_close_session,
        name = R.string.menu_item_close_session
    )
}

fun getMenuOptions(): List<MenuItemVO> =
    listOf(MenuItemVO.CloseSession)