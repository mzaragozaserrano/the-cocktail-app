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
    val id: Int,
    @StringRes val labelId: Int,
    @StringRes val nameId: Int,
    @ColorRes val textColorId: Int,
) {
    @Serializable
    data object Alcoholic :
        DrinkType(
            buttonBackgroundColorId = R.color.inverse_surface,
            dbId = R.string.db_alcoholic,
            iconBackgroundColorId = R.color.error,
            iconId = R.drawable.ic_forbidden_age,
            iconTintId = R.color.on_error,
            id = 0,
            labelId = R.string.label_alcoholic,
            nameId = R.string.type_alcoholic,
            textColorId = R.color.inverse_on_surface
        )

    @Serializable
    data object None : DrinkType(
        buttonBackgroundColorId = R.color.inverse_surface,
        dbId = R.string.db_non_alcoholic,
        iconBackgroundColorId = R.color.surface_container,
        iconId = R.drawable.ic_free,
        iconTintId = R.color.on_surface,
        id = 1,
        labelId = R.string.label_free,
        nameId = R.string.type_non_alcoholic,
        textColorId = R.color.inverse_on_surface
    )

    @Serializable
    data object Optional :
        DrinkType(
            buttonBackgroundColorId = R.color.inverse_surface,
            dbId = R.string.db_optional_alcohol,
            iconBackgroundColorId = R.color.surface_container_highest,
            iconId = R.drawable.ic_optional,
            iconTintId = R.color.on_surface,
            id = 2,
            labelId = R.string.label_optional,
            nameId = R.string.type_optional_alcohol,
            textColorId = R.color.inverse_on_surface
        )
}

fun getDrinkTypeList() = listOf(DrinkType.Alcoholic, DrinkType.None, DrinkType.Optional)