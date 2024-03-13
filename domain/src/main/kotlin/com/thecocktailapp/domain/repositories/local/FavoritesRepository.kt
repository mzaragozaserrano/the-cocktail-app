package com.thecocktailapp.domain.repositories.local

import com.thecocktailapp.core.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun addDrink(drinkId: Int): Boolean
    suspend fun getAllFavorites(): Flow<Result<List<Int>>>
    fun isFavoriteDrink(drinkId: Int): Boolean
    fun removeDrink(drinkId: Int): Boolean
}