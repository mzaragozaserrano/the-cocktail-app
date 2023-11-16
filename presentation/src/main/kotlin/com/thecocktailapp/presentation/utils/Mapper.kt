package com.thecocktailapp.presentation.utils

import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO

fun ErrorBO.transform(): ErrorVO = when (this) {
    is ErrorBO.Basic -> ErrorVO.Basic(id = id)
    is ErrorBO.Connectivity -> ErrorVO.Connectivity
    is ErrorBO.DataNotFound -> ErrorVO.DataNotFound
    is ErrorBO.DeserializingJSON -> ErrorVO.DeserializingJSON
    is ErrorBO.LoadingData -> ErrorVO.LoadingData
    is ErrorBO.LoadingURL -> ErrorVO.LoadingURL
}

fun DrinkBO.transform(): DrinkVO = DrinkVO(
    category = category,
    dateModified = dateModified,
    glass = glass,
    id = id,
    ingredients = createFormattedIngredients(
        ingredients = listIngredients,
        measures = listMeasures
    ),
    isAlcoholic = isAlcoholic,
    name = name,
    instructions = instructions,
    urlImage = urlImage
)

fun createFormattedIngredients(ingredients: List<String>, measures: List<String>): List<String> {
    val list = mutableListOf<String>()
    (0..14).forEach { index ->
        val ingredient = ingredients[index]
        val measure = measures[index]
        if (ingredient.isNotEmpty() && measure.isNotEmpty()) {
            list.add("$ingredient - $measure")
        }
    }
    return list
}