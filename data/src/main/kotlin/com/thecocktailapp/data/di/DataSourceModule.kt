package com.thecocktailapp.data.di

import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSource
import com.thecocktailapp.data.datasources.local.preferences.PreferencesDataSourceImpl
import com.thecocktailapp.data.datasources.services.CocktailDataSource
import com.thecocktailapp.data.datasources.services.CocktailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindCocktailDataSource(dataSourceImpl: CocktailDataSourceImpl): CocktailDataSource

    @Binds
    fun bindPreferencesDataSource(dataSourceImpl: PreferencesDataSourceImpl): PreferencesDataSource

}