package com.thecocktailapp.repositories

import com.mzs.core.data.datasources.local.ResourcesDataSource
import com.mzs.core.data.utils.ResultData
import com.mzs.core.domain.utils.Result
import com.thecocktailapp.data.datasources.services.CocktailDataSource
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeCocktailRepositoryImpl @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val resourcesDataSource: ResourcesDataSource,
) : CocktailRepository {

    override suspend fun getDrinkById(id: Int): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getDrinkById(id = id)) {
                    is ResultData.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource))
                    }

                    is ResultData.Error<*> -> {
                        Result.Response.Error((result.code as ErrorDTO).transform())
                    }
                }
            )
        }

    override suspend fun getDrinksByType(alcoholic: String): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getDrinksByType(alcoholic = alcoholic)) {
                    is ResultData.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource))
                    }

                    is ResultData.Error<*> -> {
                        Result.Response.Error((result.code as ErrorDTO).transform())
                    }
                }
            )
        }

    override suspend fun getRandomDrink(): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getRandomDrink()) {
                    is ResultData.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource))
                    }

                    is ResultData.Error<*> -> {
                        Result.Response.Error((result.code as ErrorDTO).transform())
                    }
                }
            )
        }

    override fun showRandomCocktail(): Boolean {
        TODO("Aquí hay que implementar el test para saber si se va a mostrar o no el splash")
    }

}