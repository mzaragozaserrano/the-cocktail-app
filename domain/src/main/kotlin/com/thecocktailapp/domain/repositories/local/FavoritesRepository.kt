package com.thecocktailapp.domain.repositories.local

import com.thecocktailapp.domain.bo.DrinkBO

interface FavoritesRepository {
    fun addDrink(drink: DrinkBO): Boolean
    fun getAllFavorites(): List<DrinkBO>
    fun isFavoriteDrink(drinkId: Int): Boolean
    fun removeDrink(drink: DrinkBO): Boolean
}