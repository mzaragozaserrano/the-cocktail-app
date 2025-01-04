package com.thecocktailapp.presentation.vo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thecocktailapp.presentation.R

import kotlinx.serialization.Serializable

@Serializable
sealed class DrinkType(
    @ColorRes val buttonBackgroundColorId: Int,
    @StringRes val dbId: Int,
    @ColorRes val iconBackgroundColorId: Int,
    @DrawableRes val iconId: Int,
    @ColorRes val iconTintId: Int,
    @StringRes val labelId: Int,
    @StringRes val nameId: Int,
    @ColorRes val textColorId: Int,
) {
    data object Alcoholic :
        DrinkType(
            buttonBackgroundColorId = R.color.color_error,
            dbId = R.string.db_alcoholic,
            iconBackgroundColorId = R.color.color_error_container,
            iconId = R.drawable.ic_forbidden_age,
            iconTintId = R.color.color_on_error_container,
            labelId = R.string.label_alcoholic,
            nameId = R.string.type_alcoholic,
            textColorId = R.color.color_on_error
        )

    data object None : DrinkType(
        buttonBackgroundColorId = R.color.color_error,
        dbId = R.string.db_non_alcoholic,
        iconBackgroundColorId = R.color.color_error_container,
        iconId = R.drawable.ic_free,
        iconTintId = R.color.color_on_error_container,
        labelId = R.string.label_free,
        nameId = R.string.type_non_alcoholic,
        textColorId = R.color.color_on_error
    )

    data object Optional :
        DrinkType(
            buttonBackgroundColorId = R.color.color_error,
            dbId = R.string.db_optional_alcohol,
            iconBackgroundColorId = R.color.color_error_container,
            iconId = R.drawable.ic_optional,
            iconTintId = R.color.color_on_error_container,
            labelId = R.string.label_optional,
            nameId = R.string.type_optional_alcohol,
            textColorId = R.color.color_on_error
        )
}

fun getDrinkTypeList() = listOf(DrinkType.Alcoholic, DrinkType.None, DrinkType.Optional)