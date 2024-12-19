package com.thecocktailapp.presentation.utils

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.thecocktailapp.presentation.base.TheCocktailAppBaseActivity

fun Fragment.setUpMenuToolbar(
    allowGoBack: Boolean = false,
    drawerLayout: DrawerLayout,
    @ColorRes scrimColorId: Int,
    @ColorRes titleColorId: Int,
    @StringRes titleTextId: Int? = null,
    toolbar: Toolbar,
    @ColorRes toolbarBackgroundColorId: Int,
    toolbarTitle: AppCompatTextView? = null,
) {
    (activity as TheCocktailAppBaseActivity<*, *, *, *, *, *>).setUpMenuToolbar(
        allowGoBack = allowGoBack,
        drawerLayout = drawerLayout,
        scrimColorId = scrimColorId,
        titleColorId = titleColorId,
        titleTextId = titleTextId,
        toolbar = toolbar,
        toolbarBackgroundColorId = toolbarBackgroundColorId,
        toolbarTitle = toolbarTitle
    )
}