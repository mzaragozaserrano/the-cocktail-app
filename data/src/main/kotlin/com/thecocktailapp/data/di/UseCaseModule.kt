package com.thecocktailapp.data.di

import com.thecocktailapp.domain.usecases.GetRandomCocktail
import com.thecocktailapp.domain.usecases.GetRandomCocktailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetRandomCocktail(useCaseImpl: GetRandomCocktailUseCaseImpl): GetRandomCocktail

}