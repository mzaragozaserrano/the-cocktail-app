package com.thecocktailapp.domain.repositories

import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.Result
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
    suspend fun getRandomCocktail(): Flow<Result<CocktailBO>>
}