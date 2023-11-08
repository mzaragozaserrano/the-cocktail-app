package com.thecocktailapp.presentation.view.utils

import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.presentation.view.vo.DrinkVO
import java.util.Locale

fun DrinkBO.transform(): DrinkVO = DrinkVO(
    category = strCategory,
    dateModified = dateModified,
    glass = strGlass,
    id = idDrink,
    ingredients = (1..15).mapNotNull { i ->
        if (!this[i]?.first.isNullOrBlank()) {
            "${this[1]?.second} ${this[1]?.first}"
        } else {
            null
        }
    },
    isAlcoholic = strAlcoholic == "Alcoholic",
    name = strDrink,
    instructions = when (Locale.getDefault().language) {
        "es" -> strInstructionsES ?: strInstructions
        "fr" -> strInstructionsFR ?: strInstructions
        "de" -> strInstructionsDE ?: strInstructions
        "it" -> strInstructionsIT
        else -> strInstructions
    },
    urlImage = strDrinkThumb
)