package com.thecocktailapp.domain.bo

import com.thecocktailapp.domain.utils.extensions.safeLet

data class DrinkBO(
    val dateModified: String?,
    val idDrink: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkAlternate: String?,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: String?,
    val strImageAttribution: String?,
    val strImageSource: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strInstructions: String,
    val strInstructionsDE: String?,
    val strInstructionsES: String?,
    val strInstructionsFR: String?,
    val strInstructionsIT: String,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
) {
    operator fun get(index: Int): Pair<String, String>? {
        return when (index) {
            1 -> {
                safeLet(strIngredient1, strMeasure1) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            2 -> {
                safeLet(strIngredient2, strMeasure2) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            3 -> {
                safeLet(strIngredient3, strMeasure3) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            4 -> {
                safeLet(strIngredient4, strMeasure4) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            5 -> {
                safeLet(strIngredient5, strMeasure5) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            6 -> {
                safeLet(strIngredient6, strMeasure6) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            7 -> {
                safeLet(strIngredient7, strMeasure7) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            8 -> {
                safeLet(strIngredient8, strMeasure8) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            9 -> {
                safeLet(strIngredient9, strMeasure9) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            10 -> {
                safeLet(strIngredient10, strMeasure10) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            11 -> {
                safeLet(strIngredient11, strMeasure11) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            12 -> {
                safeLet(strIngredient12, strMeasure12) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            13 -> {
                safeLet(strIngredient13, strMeasure13) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            14 -> {
                safeLet(strIngredient14, strMeasure14) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }

            15 -> {
                safeLet(strIngredient15, strMeasure15) { ingredient, measure ->
                    Pair(ingredient, measure)
                }
            }
            else -> null
        }
    }
}