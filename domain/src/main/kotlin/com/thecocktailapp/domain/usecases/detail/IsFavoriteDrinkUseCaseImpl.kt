package com.thecocktailapp.domain.usecases.detail

import com.thecocktailapp.core.domain.usecases.SyncUseCase
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

typealias IsFavoriteDrink = SyncUseCase<IsFavoriteDrinkUseCaseImpl.Params, @JvmSuppressWildcards Boolean>

class IsFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : IsFavoriteDrink() {

    data class Params(val drinkId: Int)

    override fun invoke(params: Params): Boolean =
        favoritesRepository.isFavoriteDrink(drinkId = params.drinkId)

}