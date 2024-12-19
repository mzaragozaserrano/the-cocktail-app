package com.thecocktailapp.domain.usecases.common

import com.mzs.core.domain.usecases.SyncUseCaseNoParams
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

typealias GetFavoriteDrinks = SyncUseCaseNoParams<@JvmSuppressWildcards List<DrinkBO>>

class GetFavoriteDrinksUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : GetFavoriteDrinks() {

    override fun invoke(): List<DrinkBO> = favoritesRepository.getAllFavorites()

}