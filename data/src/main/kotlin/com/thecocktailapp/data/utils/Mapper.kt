package com.thecocktailapp.data.utils

import com.thecocktailapp.core.data.datasources.local.ResourcesDataSource
import com.thecocktailapp.data.R
import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.DrinkDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import java.util.Locale

fun CocktailDTO.transform(resourcesDataSource: ResourcesDataSource): CocktailBO =
    CocktailBO(drinks = drinks?.map { it.transform(resourcesDataSource = resourcesDataSource) }
        .orEmpty())

fun DrinkDTO.transform(resourcesDataSource: ResourcesDataSource): DrinkBO {
    val listIngredients = createListIngredients(resourcesDataSource = resourcesDataSource)
    return DrinkBO(
        alcoholic = strAlcoholic.orEmpty(),
        category = resourcesDataSource.getStringOrResource(
            str = strCategory,
            resId = R.string.category_drink_default
        ),
        dateModified = dateModified.orEmpty(),
        id = idDrink,
        instructions = getInstructions(resourcesDataSource),
        glass = strGlass.orEmpty(),
        listIngredients = listIngredients,
        listMeasures = createListMeasures(
            listIngredients = listIngredients,
            resourcesDataSource = resourcesDataSource
        ),
        name = resourcesDataSource.getStringOrResource(
            str = strDrink,
            resId = R.string.name_drink_default
        ),
        urlImage = strDrinkThumb.orEmpty()
    )
}

private fun DrinkDTO.getInstructions(resourcesDataSource: ResourcesDataSource): String =
    when (Locale.getDefault().language) {
        "es" -> strInstructionsES.orDefault(resourcesDataSource = resourcesDataSource)
        "fr" -> strInstructionsFR.orDefault(resourcesDataSource = resourcesDataSource)
        "de" -> strInstructionsDE.orDefault(resourcesDataSource = resourcesDataSource)
        "it" -> strInstructionsIT.orDefault(resourcesDataSource = resourcesDataSource)
        else -> strInstructions.orDefault(resourcesDataSource = resourcesDataSource)
    }

private fun String?.orDefault(resourcesDataSource: ResourcesDataSource): String =
    resourcesDataSource.getStringOrResource(str = this, resId = R.string.instructions_drink_default)

private fun DrinkDTO.createListIngredients(resourcesDataSource: ResourcesDataSource): List<String> =
    listOf(
        strIngredient1.orEmpty(),
        strIngredient2.orEmpty(),
        strIngredient3.orEmpty(),
        strIngredient4.orEmpty(),
        strIngredient5.orEmpty(),
        strIngredient6.orEmpty(),
        strIngredient7.orEmpty(),
        strIngredient8.orEmpty(),
        strIngredient9.orEmpty(),
        strIngredient10.orEmpty(),
        strIngredient11.orEmpty(),
        strIngredient12.orEmpty(),
        strIngredient13.orEmpty(),
        strIngredient14.orEmpty(),
        strIngredient15.orEmpty()
    ).run {
        if (any { it.isNotEmpty() }) {
            this
        } else {
            listOf(
                resourcesDataSource.getStringFromResource(resId = R.string.ingredients_drink_default)
            )
        }
    }

private fun DrinkDTO.createListMeasures(
    listIngredients: List<String>,
    resourcesDataSource: ResourcesDataSource,
): List<String> =
    if (resourcesDataSource.getStringFromResource(resId = R.string.ingredients_drink_default) != listIngredients.first()) {
        listOf(
            strMeasure1.orEmpty(),
            strMeasure2.orEmpty(),
            strMeasure3.orEmpty(),
            strMeasure4.orEmpty(),
            strMeasure5.orEmpty(),
            strMeasure6.orEmpty(),
            strMeasure7.orEmpty(),
            strMeasure8.orEmpty(),
            strMeasure9.orEmpty(),
            strMeasure10.orEmpty(),
            strMeasure11.orEmpty(),
            strMeasure12.orEmpty(),
            strMeasure13.orEmpty(),
            strMeasure14.orEmpty(),
            strMeasure15.orEmpty()
        )
    } else {
        listOf()
    }

fun ErrorDTO.transform(): ErrorBO = when (this) {
    is ErrorDTO.Connectivity -> ErrorBO.Connectivity
    is ErrorDTO.DataNotFound -> ErrorBO.DataNotFound
    is ErrorDTO.DeserializingJSON -> ErrorBO.DeserializingJSON
    is ErrorDTO.Generic -> ErrorBO.Generic(id = id)
    is ErrorDTO.LoadingData -> ErrorBO.LoadingData
    is ErrorDTO.LoadingURL -> ErrorBO.LoadingURL
}

fun DrinkBO.transform(): DrinkDTO {
    val ingredientsMap = listIngredients
        .take(15)
        .mapIndexed { index, ingredient -> "strIngredient${index + 1}" to ingredient }
        .toMap()
    val measuresMap = listMeasures
        .take(15)
        .mapIndexed { index, measure -> "strMeasure${index + 1}" to measure }
        .toMap()
    return DrinkDTO(
        dateModified = dateModified,
        idDrink = id,
        strAlcoholic = alcoholic,
        strCategory = category,
        strDrink = name,
        strDrinkThumb = urlImage,
        strGlass = glass,
        strIngredient1 = ingredientsMap["strIngredient1"],
        strIngredient2 = ingredientsMap["strIngredient2"],
        strIngredient3 = ingredientsMap["strIngredient3"],
        strIngredient4 = ingredientsMap["strIngredient4"],
        strIngredient5 = ingredientsMap["strIngredient5"],
        strIngredient6 = ingredientsMap["strIngredient6"],
        strIngredient7 = ingredientsMap["strIngredient7"],
        strIngredient8 = ingredientsMap["strIngredient8"],
        strIngredient9 = ingredientsMap["strIngredient9"],
        strIngredient10 = ingredientsMap["strIngredient10"],
        strIngredient11 = ingredientsMap["strIngredient11"],
        strIngredient12 = ingredientsMap["strIngredient12"],
        strIngredient13 = ingredientsMap["strIngredient13"],
        strIngredient14 = ingredientsMap["strIngredient14"],
        strIngredient15 = ingredientsMap["strIngredient15"],
        strInstructions = instructions,
        strMeasure1 = measuresMap["strMeasure1"],
        strMeasure2 = measuresMap["strMeasure2"],
        strMeasure3 = measuresMap["strMeasure3"],
        strMeasure4 = measuresMap["strMeasure4"],
        strMeasure5 = measuresMap["strMeasure5"],
        strMeasure6 = measuresMap["strMeasure6"],
        strMeasure7 = measuresMap["strMeasure7"],
        strMeasure8 = measuresMap["strMeasure8"],
        strMeasure9 = measuresMap["strMeasure9"],
        strMeasure10 = measuresMap["strMeasure10"],
        strMeasure11 = measuresMap["strMeasure11"],
        strMeasure12 = measuresMap["strMeasure12"],
        strMeasure13 = measuresMap["strMeasure13"],
        strMeasure14 = measuresMap["strMeasure14"],
        strMeasure15 = measuresMap["strMeasure15"],
    )
}