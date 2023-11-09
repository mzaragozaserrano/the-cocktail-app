package com.thecocktailapp.domain.repositories

import com.mzaragozaserrano.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
    suspend fun getRandomDrink(): Flow<Result<CocktailBO>>
}