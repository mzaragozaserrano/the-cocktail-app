package com.thecocktailapp.data.di

import com.thecocktailapp.data.datasource.CocktailDataSource
import com.thecocktailapp.data.datasource.CocktailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindCocktailDataSource(dataSourceImpl: CocktailDataSourceImpl): CocktailDataSource

}