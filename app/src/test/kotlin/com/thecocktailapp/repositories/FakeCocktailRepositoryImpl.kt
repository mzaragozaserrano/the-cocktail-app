package com.thecocktailapp.repositories

/*import androidx.annotation.StringRes
import com.mzs.core.data.datasources.local.ResourcesDataSource
import com.mzs.core.domain.bo.Result
import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSource
import com.thecocktailapp.data.datasources.remote.CocktailDataSource
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.dto.ResultDTO
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.repositories.remote.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject*/
/*

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
                    is ResultDTO.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
                    }

                    is ResultDTO.Error<*> -> {
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
                    is ResultDTO.Response -> {
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
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
                        preferencesDataSource.saveFirstAccessDate("11/03/2024")
                        Result.Response.Success(result.data.transform(resourcesDataSource = resourcesDataSource))
                    }

                    is ResultDTO.Error<*> -> {
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

}*/
