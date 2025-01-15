package com.thecocktailapp.presentation.di

import com.thecocktailapp.presentation.viewmodels.ComposeViewModel
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.viewmodels.FavoritesViewModel
import com.thecocktailapp.presentation.viewmodels.HomeViewModel
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    // ViewModels
    viewModelOf(::ComposeViewModel)
    viewModelOf(::DetailDrinkViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SplashViewModel)
}