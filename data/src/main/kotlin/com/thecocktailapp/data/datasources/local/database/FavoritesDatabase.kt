package com.thecocktailapp.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thecocktailapp.data.entity.Drink

@Database(entities = [Drink::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDataSource(): FavoritesDataSource
}