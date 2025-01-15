package com.thecocktailapp

import android.app.Application
import com.mzs.core.data.di.coreDataModule
import com.mzs.core.domain.di.coreDomainModule
import com.thecocktailapp.data.di.dataModule
import com.thecocktailapp.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheCocktailAppMVI : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheCocktailAppMVI)
            modules(coreDataModule, coreDomainModule, dataModule, domainModule)
        }
    }

}