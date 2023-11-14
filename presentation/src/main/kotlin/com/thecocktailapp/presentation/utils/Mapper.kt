package com.thecocktailapp.presentation.utils

import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO
import java.util.Locale

fun ErrorBO.transform(): ErrorVO = when (this) {
    is ErrorBO.Basic -> ErrorVO.Basic(id = id)
    is ErrorBO.Connectivity -> ErrorVO.Connectivity
    is ErrorBO.DataNotFound -> ErrorVO.DataNotFound
    is ErrorBO.DeserializingJSON -> ErrorVO.DeserializingJSON
    is ErrorBO.LoadingData -> ErrorVO.LoadingData
    is ErrorBO.LoadingURL -> ErrorVO.LoadingURL
}

fun DrinkBO.transform(): DrinkVO = DrinkVO(
    category = strCategory,
    dateModified = dateModified,
    glass = strGlass,
    id = idDrink,
    ingredients = (1..15).mapNotNull { i ->
        if (!this[i]?.first.isNullOrBlank()) {
            "${this[i]?.first} - ${this[i]?.second}"
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