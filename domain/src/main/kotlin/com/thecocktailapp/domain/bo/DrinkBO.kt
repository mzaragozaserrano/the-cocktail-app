package com.thecocktailapp.domain.bo

data class DrinkBO(
    val category: String,
    val dateModified: String,
    val id: String,
    val instructions: String,
    val isAlcoholic: Boolean,
    val glass: String,
    val listIngredients: List<String>,
    val listMeasures: List<String>,
    val name: String,
    val urlImage: String,
)