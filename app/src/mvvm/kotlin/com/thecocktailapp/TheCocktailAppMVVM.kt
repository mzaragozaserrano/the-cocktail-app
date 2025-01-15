package com.thecocktailapp

import android.app.Application
import com.mzs.core.data.di.coreDataModule
import com.mzs.core.domain.di.coreDomainModule
import com.thecocktailapp.data.di.dataModule
import com.thecocktailapp.domain.di.domainModule
import com.thecocktailapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheCocktailAppMVVM : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheCocktailAppMVVM)
            modules(coreDataModule, coreDomainModule, dataModule, domainModule, presentationModule)
        }
    }

}