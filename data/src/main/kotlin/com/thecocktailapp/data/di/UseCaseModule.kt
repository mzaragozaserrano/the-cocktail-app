package com.thecocktailapp.data.di

import com.thecocktailapp.domain.usecases.main.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.main.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.domain.usecases.splash.GetRandomDrink
import com.thecocktailapp.domain.usecases.splash.GetRandomDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrink
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrinkImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetDrinkById(useCaseImpl: GetDrinkByIdUseCaseImpl): GetDrinkById

    @Binds
    fun bindGetRandomDrink(useCaseImpl: GetRandomDrinkUseCaseImpl): GetRandomDrink

    @Binds
    fun bindShowRandomDrink(useCaseImpl: ShowRandomDrinkImpl): ShowRandomDrink

}