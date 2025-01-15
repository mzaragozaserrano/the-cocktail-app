package com.thecocktailapp.data.di

import android.content.Context
import androidx.room.Room
import com.thecocktailapp.data.datasources.local.database.FavoritesDataSource
import com.thecocktailapp.data.datasources.local.database.FavoritesDatabase
import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSource
import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSourceImpl
import com.thecocktailapp.data.datasources.remote.CocktailDataSource
import com.thecocktailapp.data.datasources.remote.CocktailDataSourceImpl
import com.thecocktailapp.data.repositories.local.FavoritesRepositoryImpl
import com.thecocktailapp.data.repositories.remote.CocktailRepositoryImpl
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.repositories.remote.CocktailRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun provideFavoritesDatabase(context: Context): FavoritesDatabase =
    Room.databaseBuilder(
        context = context,
        klass = FavoritesDatabase::class.java,
        name = "FavoritesDB"
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

fun provideFavoritesDataSource(favoritesDatabase: FavoritesDatabase): FavoritesDataSource =
    favoritesDatabase.favoritesDataSource()

val dataModule = module {
    // Database
    single { provideFavoritesDatabase(context = androidContext()) }
    single { provideFavoritesDataSource(favoritesDatabase = get()) }
    // DataSources
    singleOf(::CocktailDataSourceImpl) { bind<CocktailDataSource>() }
    singleOf(::PreferencesDataSourceImpl) { bind<PreferencesDataSource>() }
    // Repositories
    singleOf(::CocktailRepositoryImpl) { bind<CocktailRepository>() }
    singleOf(::FavoritesRepositoryImpl) { bind<FavoritesRepository>() }
}
