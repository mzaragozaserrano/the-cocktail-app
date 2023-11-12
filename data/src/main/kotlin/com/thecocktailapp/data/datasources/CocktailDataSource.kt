package com.thecocktailapp.data.datasources

import com.mzaragozaserrano.data.utils.ResultData
import com.thecocktailapp.data.dto.CocktailDTO


interface CocktailDataSource {
    suspend fun getDrinkById(id: Int): ResultData<CocktailDTO>
    suspend fun getRandomDrink(): ResultData<CocktailDTO>
}