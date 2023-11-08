package com.thecocktailapp.data.utils

import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.DrinkDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO

fun CocktailDTO.transform(): CocktailBO = CocktailBO(drinks = drinks.map { it.transform() })

fun DrinkDTO.transform(): DrinkBO = DrinkBO(
    dateModified = dateModified,
    idDrink = idDrink,
    strAlcoholic = strAlcoholic,
    strCategory = strCategory,
    strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
    strDrink = strDrink,
    strDrinkAlternate = strDrinkAlternate,
    strDrinkThumb = strDrinkThumb,
    strGlass = strGlass,
    strIBA = strIBA,
    strImageAttribution = strImageAttribution,
    strImageSource = strImageSource,
    strIngredient1 = strIngredient1,
    strIngredient2 = strIngredient2,
    strIngredient3 = strIngredient3,
    strIngredient4 = strIngredient4,
    strIngredient5 = strIngredient5,
    strIngredient6 = strIngredient6,
    strIngredient7 = strIngredient7,
    strIngredient8 = strIngredient8,
    strIngredient9 = strIngredient9,
    strIngredient10 = strIngredient10,
    strIngredient11 = strIngredient11,
    strIngredient12 = strIngredient12,
    strIngredient13 = strIngredient13,
    strIngredient14 = strIngredient14,
    strIngredient15 = strIngredient15,
    strInstructions = strInstructions,
    strMeasure1 = strMeasure1,
    strMeasure2 = strMeasure2,
    strMeasure3 = strMeasure3,
    strMeasure4 = strMeasure4,
    strMeasure5 = strMeasure5,
    strMeasure6 = strMeasure6,
    strMeasure7 = strMeasure7,
    strMeasure8 = strMeasure8,
    strMeasure9 = strMeasure9,
    strMeasure10 = strMeasure10,
    strMeasure11 = strMeasure11,
    strMeasure12 = strMeasure12,
    strMeasure13 = strMeasure13,
    strMeasure14 = strMeasure14,
    strMeasure15 = strMeasure15
)

fun ErrorDTO.transform(): ErrorBO = when (this) {
    is ErrorDTO.Basic -> ErrorBO.Basic(id = id)
    is ErrorDTO.Connectivity -> ErrorBO.Connectivity
    is ErrorDTO.DataNotFound -> ErrorBO.DataNotFound
    is ErrorDTO.DeserializingJSON -> ErrorBO.DeserializingJSON
    is ErrorDTO.LoadingData -> ErrorBO.LoadingData
    is ErrorDTO.LoadingURL -> ErrorBO.LoadingURL
}