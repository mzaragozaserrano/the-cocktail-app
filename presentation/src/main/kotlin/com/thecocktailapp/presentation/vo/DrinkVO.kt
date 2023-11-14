package com.thecocktailapp.presentation.vo

import java.io.Serializable

data class DrinkVO(
    val category: String,
    val dateModified: String?,
    val glass: String,
    val id: String,
    val isAlcoholic: Boolean,
    val name: String,
    val ingredients: List<String>,
    val instructions: String,
    val urlImage: String,
) : Serializable