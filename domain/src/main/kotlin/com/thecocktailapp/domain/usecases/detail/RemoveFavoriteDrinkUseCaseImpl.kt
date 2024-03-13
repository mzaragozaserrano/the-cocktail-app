package com.thecocktailapp.domain.usecases.detail

import com.thecocktailapp.core.domain.usecases.SyncUseCase
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

typealias RemoveFavoriteDrink = SyncUseCase<RemoveFavoriteDrinkUseCaseImpl.Params, @JvmSuppressWildcards Boolean>

class RemoveFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : RemoveFavoriteDrink() {

    data class Params(val drinkId: Int)

    override fun invoke(params: Params): Boolean =
        favoritesRepository.removeDrink(drinkId = params.drinkId)

}