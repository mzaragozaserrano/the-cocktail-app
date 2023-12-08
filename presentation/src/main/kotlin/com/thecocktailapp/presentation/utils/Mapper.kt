package com.thecocktailapp.presentation.utils

import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO

fun ErrorBO.transform(): ErrorVO = when (this) {
    is ErrorBO.Connectivity -> ErrorVO.Connectivity
    is ErrorBO.DataNotFound -> ErrorVO.DataNotFound
    is ErrorBO.DeserializingJSON -> ErrorVO.DeserializingJSON
    is ErrorBO.Generic -> ErrorVO.Generic(id = id)
    is ErrorBO.LoadingData -> ErrorVO.LoadingData
    is ErrorBO.LoadingURL -> ErrorVO.LoadingURL
}

fun DrinkBO.transform(): DrinkVO = DrinkVO(
    category = category,
    dateModified = dateModified,
    drinkType = createDrinkType(),
    glass = glass,
    id = id,
    ingredients = createIngredientsFormatted(),
    instructions = instructions,
    name = name,
    urlImage = urlImage
)

fun DrinkBO.createDrinkType(): DrinkType? = when (alcoholic) {
    "Alcoholic" -> {
        DrinkType.Alcoholic
    }

    "Optional alcohol" -> {
        DrinkType.Optional
    }

    "Non alcoholic" -> {
        DrinkType.None
    }

    else -> {
        null
    }
}

fun DrinkBO.createIngredientsFormatted(): List<String> {
    val list = mutableListOf<String>()
    if (listMeasures.isNotEmpty()) {
        (0..14).forEach { index ->
            val ingredient = listIngredients[index]
            val measure = listMeasures[index]
            if (ingredient.isNotEmpty() && measure.isNotEmpty()) {
                list.add("$ingredient - $measure")
            }
        }
    } else {
        list.add(listIngredients.first())
    }
    return list
}