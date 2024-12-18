package com.thecocktailapp.repositories

import androidx.annotation.StringRes
import com.thecocktailapp.core.data.datasources.local.ResourcesDataSource
import com.thecocktailapp.core.data.utils.ResultData
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSource
import com.thecocktailapp.data.datasources.services.CocktailDataSource
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.repositories.services.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeCocktailRepositoryImpl @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val resourcesDataSource: ResourcesDataSource,
) : CocktailRepository {

    override suspend fun getDrinkById(id: Int): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getDrinkById(id = id)) {
                    is ResultData.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
                    }

                    is ResultData.Error<*> -> {
                        Result.Response.Error(code = (result.code as ErrorDTO).transform())
                    }
                }
            )
        }

    override suspend fun getDrinksByType(@StringRes dbId: Int): Flow<Result<CocktailBO>> =
        flow {
            emit(Result.Loading)
            emit(
                when (val result = cocktailDataSource.getDrinksByType(
                    alcoholic = when (dbId) {
                        0 -> {
                            "Alcoholic"
                        }

                        1 -> {
                            "Non_Alcoholic"
                        }

                        else -> {
                            "Optional_Alcohol"
                        }
                    }
                )) {
                    is ResultData.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
                    }

                    is ResultData.Error<*> -> {
                        Result.Response.Error(code = (result.code as ErrorDTO).transform())
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
                        preferencesDataSource.saveFirstAccessDate("11/03/2024")
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
                    }

                    is ResultData.Error<*> -> {
                        Result.Response.Error(code = (result.code as ErrorDTO).transform())
                    }
                }
            )
        }

    override fun showRandomCocktail(): Boolean {
        val currentDate = "11/03/2024"
        val accessDate = preferencesDataSource.getFirstAccessDate()
        return accessDate == null || accessDate != currentDate
    }

}