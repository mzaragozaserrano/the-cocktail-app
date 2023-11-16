package com.thecocktailapp.data.utils

import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.DrinkDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import java.util.Locale

fun CocktailDTO.transform(): CocktailBO =
    CocktailBO(drinks = drinks?.map { it.transform() }.orEmpty())

fun DrinkDTO.transform(): DrinkBO = DrinkBO(
    category = strCategory.orEmpty(),
    dateModified = dateModified.orEmpty(),
    id = idDrink.orEmpty(),
    instructions = when (Locale.getDefault().language) {
        "es" -> strInstructionsES ?: strInstructions
        "fr" -> strInstructionsFR ?: strInstructions
        "de" -> strInstructionsDE ?: strInstructions
        "it" -> strInstructionsIT
        else -> strInstructions
    }.orEmpty(),
    isAlcoholic = strAlcoholic == "Alcoholic",
    glass = strGlass.orEmpty(),
    listIngredients = listOf(
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
    ),
    listMeasures = listOf(
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
    ),
    name = strDrink.orEmpty(),
    urlImage = strDrinkThumb.orEmpty()
)

fun ErrorDTO.transform(): ErrorBO = when (this) {
    is ErrorDTO.Basic -> ErrorBO.Basic(id = id)
    is ErrorDTO.Connectivity -> ErrorBO.Connectivity
    is ErrorDTO.DataNotFound -> ErrorBO.DataNotFound
    is ErrorDTO.DeserializingJSON -> ErrorBO.DeserializingJSON
    is ErrorDTO.LoadingData -> ErrorBO.LoadingData
    is ErrorDTO.LoadingURL -> ErrorBO.LoadingURL
}