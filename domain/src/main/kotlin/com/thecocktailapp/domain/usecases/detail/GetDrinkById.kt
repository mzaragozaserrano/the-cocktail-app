package com.thecocktailapp.domain.usecases.detail

import com.thecocktailapp.core.domain.repositories.NetworkRepository
import com.thecocktailapp.core.domain.usecases.FlowUseCase
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetDrinkById = FlowUseCase<GetDrinkByIdUseCaseImpl.Params, @JvmSuppressWildcards Result<CocktailBO>, ErrorBO>

class GetDrinkByIdUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetDrinkById(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    data class Params(val id: Int)

    override suspend fun run(params: Params): Flow<Result<CocktailBO>> =
        cocktailRepository.getDrinkById(params.id)

}