package com.thecocktailapp.data.repositories.local

import com.mzs.core.data.datasources.local.ResourcesDataSource
import com.thecocktailapp.data.datasources.local.database.FavoritesDataSource
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSource: FavoritesDataSource,
    private val resourcesDataSource: ResourcesDataSource,
) : FavoritesRepository {

    override fun addDrink(drink: DrinkBO): Boolean {
        favoritesDataSource.addDrink(drink = drink.transform())
        return true
    }

    override fun getAllFavorites(): List<DrinkBO> {
        val result = mutableListOf<DrinkBO>()
        favoritesDataSource.getAllFavorites().forEach { drink ->
            result.add(drink.transform(resourcesDataSource = resourcesDataSource))
        }
        return result
    }

    override fun isFavoriteDrink(drinkId: Int): Boolean =
        favoritesDataSource.isFavoriteDrink(id = drinkId)

    override fun removeDrink(drink: DrinkBO): Boolean {
        favoritesDataSource.removeDrink(drink = drink.transform())
        return true
    }

}