package com.thecocktailapp.di

import com.thecocktailapp.data.datasources.services.CocktailDataSource
import com.thecocktailapp.datasources.FakeCocktailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FakeDataSourceModule {

    @Binds
    fun bindFakeCocktailDataSource(dataSourceImpl: FakeCocktailDataSourceImpl): CocktailDataSource

}