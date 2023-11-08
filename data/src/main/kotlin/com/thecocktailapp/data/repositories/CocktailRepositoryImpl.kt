package com.thecocktailapp.data.repositories

import com.thecocktailapp.data.datasource.CocktailDataSource
import com.thecocktailapp.data.dto.ResultData
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.Result
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
) : CocktailRepository {
    override suspend fun getRandomCocktail(): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getRandomCocktail()) {
                    is ResultData.Response -> {
                        Result.Response.Success(result.data.transform())
                    }

                    is ResultData.Error -> {
                        Result.Response.Error(result.code.transform())
                    }
                }
            )
        }
}