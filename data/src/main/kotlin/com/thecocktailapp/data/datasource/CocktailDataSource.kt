package com.thecocktailapp.data.datasource

import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.ResultData


interface CocktailDataSource {

    suspend fun getRandomCocktail(): ResultData<CocktailDTO>

}