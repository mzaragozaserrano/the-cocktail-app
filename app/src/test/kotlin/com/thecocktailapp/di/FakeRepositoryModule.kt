package com.thecocktailapp.di

import com.thecocktailapp.domain.repositories.services.CocktailRepository
import com.thecocktailapp.repositories.FakeCocktailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FakeRepositoryModule {
    @Binds
    fun bindFakeCocktailRepository(repositoryImpl: FakeCocktailRepositoryImpl): CocktailRepository
}