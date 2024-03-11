package com.thecocktailapp.presentation.vo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.ui.R

sealed class DrinkType(
    val alcoholic: String,
    @ColorRes val buttonBackgroundColorId: Int,
    @ColorRes val iconBackgroundColorId: Int,
    @DrawableRes val iconId: Int,
    @ColorRes val iconTintId: Int,
    @StringRes val labelId: Int,
    @ColorRes val textColorId: Int,
) {
     object Alcoholic :
        DrinkType(
            alcoholic = "Alcoholic",
            buttonBackgroundColorId = R.color.color_error,
            iconBackgroundColorId = R.color.color_error_container,
            iconId = R.drawable.ic_forbidden_age,
            iconTintId = R.color.color_on_error_container,
            labelId = R.string.label_alcoholic,
            textColorId = R.color.color_on_error
        )

    object None : DrinkType(
        alcoholic = "Non alcoholic",
        buttonBackgroundColorId = R.color.color_error,
        iconBackgroundColorId = R.color.color_error_container,
        iconId = R.drawable.ic_free,
        iconTintId = R.color.color_on_error_container,
        labelId = R.string.label_free,
        textColorId = R.color.color_on_error
    )

    object Optional :
        DrinkType(
            alcoholic = "Optional alcohol",
            buttonBackgroundColorId = R.color.color_error,
            iconBackgroundColorId = R.color.color_error_container,
            iconId = R.drawable.ic_forbidden_age,
            iconTintId = R.color.color_on_error_container,
            labelId = R.string.label_optional,
            textColorId = R.color.color_on_error
        )
}