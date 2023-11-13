package com.thecocktailapp.repositories

import com.mzaragozaserrano.data.utils.ResultData
import com.mzaragozaserrano.domain.utils.Result
import com.thecocktailapp.data.datasources.CocktailDataSource
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeCocktailRepositoryImpl @Inject constructor(private val cocktailDataSource: CocktailDataSource) :
    CocktailRepository {
    override suspend fun getDrinkById(id: Int): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getDrinkById(id)) {
                    is ResultData.Response -> Result.Response.Success(result.data.transform())
                    is ResultData.Error<*> -> Result.Response.Error((result.code as ErrorDTO).transform())
                }
            )
        }

    override suspend fun getRandomDrink(): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getRandomDrink()) {
                    is ResultData.Response -> Result.Response.Success(result.data.transform())
                    is ResultData.Error<*> -> Result.Response.Error((result.code as ErrorDTO).transform())
                }
            )
        }
}