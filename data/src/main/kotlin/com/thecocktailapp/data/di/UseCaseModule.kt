package com.thecocktailapp.data.di

import com.thecocktailapp.domain.usecases.CheckPreferencesToShowRandomDrink
import com.thecocktailapp.domain.usecases.CheckPreferencesToShowRandomDrinkImpl
import com.thecocktailapp.domain.usecases.GetRandomDrink
import com.thecocktailapp.domain.usecases.GetRandomDrinkUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetRandomDrink(useCaseImpl: GetRandomDrinkUseCaseImpl): GetRandomDrink

    @Binds
    fun bindCheckPreferencesToShowRandomDrink(useCaseImpl: CheckPreferencesToShowRandomDrinkImpl): CheckPreferencesToShowRandomDrink

}