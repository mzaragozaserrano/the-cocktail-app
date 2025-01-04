package com.thecocktailapp.data.di

import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinks
import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinksUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.IsFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.home.GetDrinksByType
import com.thecocktailapp.domain.usecases.home.GetDrinksByTypeUseCaseImpl
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
    fun bindAddFavoriteDrink(useCaseImpl: AddFavoriteDrinkUseCaseImpl): AddFavoriteDrink

    @Binds
    fun bindGetDrinkById(useCaseImpl: GetDrinkByIdUseCaseImpl): GetDrinkById

    @Binds
    fun bindGetDrinksByType(useCaseImpl: GetDrinksByTypeUseCaseImpl): GetDrinksByType

    @Binds
    fun bindGetFavoriteDrinksByType(useCaseImpl: GetFavoriteDrinksUseCaseImpl): GetFavoriteDrinks

    @Binds
    fun bindGetRandomDrink(useCaseImpl: GetRandomDrinkUseCaseImpl): GetRandomDrink

    @Binds
    fun bindIsFavoriteDrink(useCaseImpl: IsFavoriteDrinkUseCaseImpl): IsFavoriteDrink

    @Binds
    fun bindRemoveFavoriteDrink(useCaseImpl: RemoveFavoriteDrinkUseCaseImpl): RemoveFavoriteDrink

    @Binds
    fun bindShowRandomDrink(useCaseImpl: ShowRandomDrinkImpl): ShowRandomDrink

}