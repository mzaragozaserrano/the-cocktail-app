package com.thecocktailapp.data.datasources.services

import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.ResultDTO

interface CocktailDataSource {
    suspend fun getDrinkById(id: Int): ResultDTO<CocktailDTO>
    suspend fun getDrinksByType(alcoholic: String): ResultDTO<CocktailDTO>
    suspend fun getRandomDrink(): ResultDTO<CocktailDTO>
}