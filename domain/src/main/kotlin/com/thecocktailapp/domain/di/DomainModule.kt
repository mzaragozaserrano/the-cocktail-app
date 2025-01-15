package com.thecocktailapp.domain.di

import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinksUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.home.GetDrinksByTypeUseCaseImpl
import com.thecocktailapp.domain.usecases.splash.GetRandomDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrinkUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    // UseCases
    factoryOf(::AddFavoriteDrinkUseCaseImpl)
    factoryOf(::GetDrinkByIdUseCaseImpl)
    factoryOf(::GetDrinksByTypeUseCaseImpl)
    factoryOf(::GetFavoriteDrinksUseCaseImpl)
    factoryOf(::GetRandomDrinkUseCaseImpl)
    factoryOf(::RemoveFavoriteDrinkUseCaseImpl)
    factoryOf(::ShowRandomDrinkUseCaseImpl)
}