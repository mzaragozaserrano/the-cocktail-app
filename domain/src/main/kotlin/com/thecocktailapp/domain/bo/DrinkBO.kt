package com.thecocktailapp.domain.bo

data class DrinkBO(
    val alcoholic: String,
    val category: String,
    val dateModified: String,
    val glass: String,
    val id: String,
    val instructions: String,
    val listIngredients: List<String?>,
    val listMeasures: List<String?>,
    val name: String,
    val urlImage: String,
)