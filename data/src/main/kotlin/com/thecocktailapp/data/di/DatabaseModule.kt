package com.thecocktailapp.data.di

import android.content.Context
import androidx.room.Room
import com.thecocktailapp.data.datasources.local.database.FavoritesDataSource
import com.thecocktailapp.data.datasources.local.database.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideFavoritesDatabase(@ApplicationContext context: Context): FavoritesDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FavoritesDatabase::class.java,
            name = "FavoritesDB"
        ).build()

    @Singleton
    @Provides
    fun provideFavoritesDataSource(favoritesDatabase: FavoritesDatabase): FavoritesDataSource =
        favoritesDatabase.favoritesDataSource()

}