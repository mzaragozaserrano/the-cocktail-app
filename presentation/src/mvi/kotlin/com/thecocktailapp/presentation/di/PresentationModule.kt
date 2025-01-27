package com.thecocktailapp.presentation.di

import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.viewmodels.KotlinViewModel
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    // ViewModels
    viewModelOf(::DetailDrinkViewModel)
    viewModelOf(::KotlinViewModel)
    viewModelOf(::SplashViewModel)
}