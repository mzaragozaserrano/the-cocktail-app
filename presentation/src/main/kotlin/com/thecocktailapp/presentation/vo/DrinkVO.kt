package com.thecocktailapp.presentation.vo

import java.io.Serializable

data class DrinkVO(
    val category: String,
    val dateModified: String?,
    val drinkType: DrinkType?,
    val glass: String,
    val id: String,
    val ingredients: List<String>,
    val instructions: String,
    val name: String,
    val urlImage: String,
) : Serializable