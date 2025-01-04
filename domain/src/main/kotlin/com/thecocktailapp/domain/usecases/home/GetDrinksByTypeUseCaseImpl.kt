package com.thecocktailapp.domain.usecases.home

import androidx.annotation.StringRes
import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.mzs.core.domain.usecases.FlowUseCase
import com.mzs.core.domain.utils.extensions.toFlowResult
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.repositories.services.CocktailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

typealias GetDrinksByType = FlowUseCase<GetDrinksByTypeUseCaseImpl.Params, @JvmSuppressWildcards Result<List<DrinkBO>>, ErrorBO>

class GetDrinksByTypeUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    private val favoritesRepository: FavoritesRepository,
    networkRepository: NetworkRepository
) : GetDrinksByType(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    data class Params(@StringRes val dbId: Int)

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun run(params: Params): Flow<Result<List<DrinkBO>>> =
        cocktailRepository.getDrinksByType(dbId = params.dbId)
            .flatMapMerge { result -> executeGetFavoriteDrinks(result) }

    private fun executeGetFavoriteDrinks(result: Result<CocktailBO>) =
        when (result) {
            is Result.Loading -> {
                Result.Loading.toFlowResult()
            }

            is Result.Response.Error<*> -> {
                Result.Response.Error(code = result.code as ErrorBO).toFlowResult()
            }

            is Result.Response.Success -> {
                val listFavoriteIds = favoritesRepository.getAllFavorites().map { it.id }
                val list = result.data.drinks
                list.map { drink ->
                    drink.isFavorite = drink.id in listFavoriteIds
                }
                Result.Response.Success(data = list).toFlowResult()
            }
        }

}