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

fun DrinkBO.transform(isFavorite: Boolean? = null): DrinkVO = DrinkVO(
    category = category,
    dateModified = dateModified,
    drinkType = createDrinkType(),
    glass = glass,
    id = id.toInt(),
    ingredients = createIngredientsFormatted(),
    instructions = instructions,
    isFavorite = isFavorite ?: this.isFavorite,
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

fun DrinkType?.getAlcoholic(): String = when (this) {
    DrinkType.Alcoholic -> {
        "Alcoholic"
    }

    DrinkType.Optional -> {
        "Optional alcohol"
    }

    DrinkType.None -> {
        "Non alcoholic"
    }

    else -> {
        ""
    }
}

fun DrinkBO.createIngredientsFormatted(): List<String> {
    val list = mutableListOf<String>()
    if (listMeasures.isNotEmpty()) {
        (0..14).forEach { index ->
            val ingredient = listIngredients[index]
            val measure = listMeasures[index]
            if (ingredient.isNullOrEmpty().not() && measure.isNullOrEmpty().not()) {
                list.add("$ingredient - $measure")
            }
        }
    }
    return list
}

fun DrinkVO.transform(): DrinkBO {
    val listIngredients = mutableListOf<String>()
    val listMeasures = mutableListOf<String>()
    ingredients.forEach { ingredient ->
        listIngredients.add(ingredient.split(" - ")[0])
        listMeasures.add(ingredient.split(" - ")[1])
    }
    return DrinkBO(
        alcoholic = drinkType.getAlcoholic(),
        category = category,
        dateModified = dateModified.orEmpty(),
        glass = glass,
        id = id.toString(),
        instructions = instructions,
        listIngredients = listIngredients,
        listMeasures = listMeasures,
        name = name,
        urlImage = urlImage
    )
}