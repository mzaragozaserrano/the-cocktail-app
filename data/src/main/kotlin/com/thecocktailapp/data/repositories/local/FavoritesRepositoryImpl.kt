package com.thecocktailapp.data.repositories.local

import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.data.datasources.local.database.FavoritesDataSource
import com.thecocktailapp.data.utils.createDrinkEntity
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSource: FavoritesDataSource,
) : FavoritesRepository {

    override fun addDrink(drinkId: Int): Boolean {
        favoritesDataSource.addDrink(drink = drinkId.createDrinkEntity())
        return true
    }

    override suspend fun getAllFavorites(): Flow<Result<List<Int>>> = flow {
        emit(Result.Loading)
        val result = mutableListOf<Int>()
        favoritesDataSource.getAllFavorites().forEach { drink ->
            result.add(drink.drinkId)
        }
        emit(Result.Response.Success(result))
    }

    override fun isFavoriteDrink(drinkId: Int): Boolean =
        favoritesDataSource.isFavoriteDrink(drinkId = drinkId)

    override fun removeDrink(drinkId: Int): Boolean {
        favoritesDataSource.removeDrink(drink = drinkId.createDrinkEntity())
        return true
    }

}