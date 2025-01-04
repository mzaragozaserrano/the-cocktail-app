package com.thecocktailapp.data.repositories.services

import androidx.annotation.StringRes
import com.mzs.core.data.datasources.local.ResourcesDataSource
import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.utils.generic.DateUtils
import com.mzs.core.domain.utils.generic.ddMMyyyy
import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSource
import com.thecocktailapp.data.datasources.services.CocktailDataSource
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.dto.ResultDTO
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.repositories.services.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val resourcesDataSource: ResourcesDataSource,
    private val dateUtils: DateUtils,
) : CocktailRepository {

    override suspend fun getDrinkById(id: Int): Flow<Result<CocktailBO>> = flow {
        emit(Result.Loading)
        emit(
            when (val result = cocktailDataSource.getDrinkById(id)) {
                is ResultDTO.Response -> {
                    Result.Response.Success(data = result.data.transform(resourcesDataSource = resourcesDataSource))
                }

                is ResultDTO.Error<*> -> {
                    Result.Response.Error(code = (result.code as ErrorDTO).transform())
                }
            }
        )
    }

    override suspend fun getDrinksByType(@StringRes dbId: Int): Flow<Result<CocktailBO>> = flow {
        emit(Result.Loading)
        emit(
            when (
                val result = cocktailDataSource.getDrinksByType(
                    alcoholic = resourcesDataSource.getStringFromResource(resId = dbId)
                )
            ) {
                is ResultDTO.Response -> {
                    Result.Response.Success(data = result.data.transform(resourcesDataSource = resourcesDataSource))
                }

                is ResultDTO.Error<*> -> {
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
                    is ResultDTO.Response -> {
                        preferencesDataSource.saveFirstAccessDate(
                            accessDate = dateUtils.getCurrentDate(formatOut = ddMMyyyy)
                        )
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
                    }

                    is ResultDTO.Error<*> -> {
                        Result.Response.Error(code = (result.code as ErrorDTO).transform())
                    }
                }
            )
        }

    override fun showRandomCocktail(): Boolean {
        val currentDate = dateUtils.getCurrentDate(formatOut = ddMMyyyy)
        val accessDate = preferencesDataSource.getFirstAccessDate()
        return accessDate == null || accessDate != currentDate
    }

}