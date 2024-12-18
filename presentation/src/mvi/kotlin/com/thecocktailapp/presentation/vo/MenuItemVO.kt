package com.thecocktailapp.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.presentation.R

sealed class MenuItemVO(@DrawableRes val iconId: Int, @StringRes val nameId: Int) {
    object CloseSession : MenuItemVO(
        iconId = R.drawable.ic_close_session,
        nameId = R.string.menu_item_close_session
    )
}

fun getMenuOptions(): List<MenuItemVO> =
    listOf(MenuItemVO.CloseSession)