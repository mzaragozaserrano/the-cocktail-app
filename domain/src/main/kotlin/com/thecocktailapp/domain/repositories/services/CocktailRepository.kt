package com.thecocktailapp.domain.repositories.services

import androidx.annotation.StringRes
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
    suspend fun getDrinkById(id: Int): Flow<Result<CocktailBO>>
    suspend fun getDrinksByType(@StringRes dbId: Int): Flow<Result<CocktailBO>>
    suspend fun getRandomDrink(): Flow<Result<CocktailBO>>
    fun showRandomCocktail(): Boolean
}