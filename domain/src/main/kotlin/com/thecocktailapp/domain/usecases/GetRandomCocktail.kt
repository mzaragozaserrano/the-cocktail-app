package com.thecocktailapp.domain.usecases

import com.mzaragozaserrano.domain.repositories.NetworkRepository
import com.mzaragozaserrano.domain.usecases.FlowUseCaseNoParams
import com.mzaragozaserrano.domain.utils.Result
import com.mzaragozaserrano.domain.utils.extension.toFlowResult
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

typealias GetRandomDrink = FlowUseCaseNoParams<@JvmSuppressWildcards Result<DrinkBO>, ErrorBO>

class GetRandomDrinkUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetRandomDrink(
    networkRepository = networkRepository,
    networkError = ErrorBO.Connectivity
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun run(): Flow<Result<DrinkBO>> =
        cocktailRepository.getRandomDrink().flatMapMerge { result ->
            when (result) {
                is Result.Loading -> {
                    Result.Loading.toFlowResult()
                }

                is Result.Response.Error<*> -> {
                    Result.Response.Error(result.code).toFlowResult()
                }

                is Result.Response.Success -> {
                    Result.Response.Success(result.data.drinks.first()).toFlowResult()
                }
            }
        }

}