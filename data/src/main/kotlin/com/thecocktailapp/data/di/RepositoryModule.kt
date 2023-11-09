package com.thecocktailapp.data.di

import com.thecocktailapp.data.repositories.CocktailRepositoryImpl
import com.thecocktailapp.data.repositories.KotlinRepositoryImpl
import com.thecocktailapp.domain.repositories.CocktailRepository
import com.thecocktailapp.domain.repositories.KotlinRepository
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
    fun bindKotlinRepository(repositoryImpl: KotlinRepositoryImpl): KotlinRepository

}