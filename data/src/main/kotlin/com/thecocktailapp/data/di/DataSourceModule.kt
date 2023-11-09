package com.thecocktailapp.data.di

import com.thecocktailapp.data.datasources.CocktailDataSource
import com.thecocktailapp.data.datasources.CocktailDataSourceImpl
import com.thecocktailapp.data.datasources.PreferencesDataSource
import com.thecocktailapp.data.datasources.PreferencesDataSourceImpl
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