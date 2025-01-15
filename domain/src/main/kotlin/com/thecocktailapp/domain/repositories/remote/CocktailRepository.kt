package com.thecocktailapp.domain.repositories.remote

import androidx.annotation.StringRes
import com.mzs.core.domain.bo.Result
import com.thecocktailapp.domain.bo.CocktailBO
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
    suspend fun getDrinkById(id: Int): Flow<Result<CocktailBO>>
    suspend fun getDrinksByType(@StringRes dbId: Int): Flow<Result<CocktailBO>>
    suspend fun getRandomDrink(): Flow<Result<CocktailBO>>
    fun showRandomCocktail(): Boolean
}