package com.thecocktailapp.data.di

import com.thecocktailapp.data.repositories.local.FavoritesRepositoryImpl
import com.thecocktailapp.data.repositories.services.CocktailRepositoryImpl
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.repositories.services.CocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCocktailRepository(repositoryImpl: CocktailRepositoryImpl): CocktailRepository

    @Binds
    fun bindFavoritesRepository(repositoryImpl: FavoritesRepositoryImpl): FavoritesRepository

}