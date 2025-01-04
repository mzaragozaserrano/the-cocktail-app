package com.thecocktailapp.domain.usecases.detail

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.mzs.core.domain.usecases.FlowUseCase
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.services.CocktailRepository
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