package com.thecocktailapp.data.datasources.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thecocktailapp.data.entity.Drink

@Dao
interface FavoritesDataSource {
    @Insert
    fun addDrink(drink: Drink)

    @Query("SELECT EXISTS(SELECT 1 FROM drink_table WHERE drinkId = :drinkId)")
    fun isFavoriteDrink(drinkId: Int): Boolean

    @Query("SELECT * FROM drink_table")
    suspend fun getAllFavorites(): List<Drink>

    @Delete
    fun removeDrink(drink: Drink)
}