package com.thecocktailapp.repositories

import com.thecocktailapp.core.data.datasources.local.ResourcesDataSource
import com.thecocktailapp.data.datasources.local.database.FavoritesDataSource
import com.thecocktailapp.data.utils.transform
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

class FakeFavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSource: FavoritesDataSource,
    private val resourcesDataSource: ResourcesDataSource,
) : FavoritesRepository {

    override fun addDrink(drink: DrinkBO): Boolean = true

    override fun getAllFavorites(): List<DrinkBO> = favoritesDataSource.getAllFavorites()
        .map { it.transform(resourcesDataSource = resourcesDataSource) }

    override fun isFavoriteDrink(drinkId: Int): Boolean = true

    override fun removeDrink(drink: DrinkBO): Boolean = true

}