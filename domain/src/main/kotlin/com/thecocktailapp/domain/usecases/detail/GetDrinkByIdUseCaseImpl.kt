package com.thecocktailapp.domain.usecases.detail

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.mzs.core.domain.usecases.FlowUseCase
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.remote.CocktailRepository
import kotlinx.coroutines.flow.Flow

class GetDrinkByIdUseCaseImpl(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : FlowUseCase<GetDrinkByIdUseCaseImpl.Params, Result<CocktailBO>, ErrorBO>(
    networkRepository = networkRepository,
    networkError = ErrorBO.Connectivity
) {

    data class Params(val id: Int)

    override suspend fun run(params: Params): Flow<Result<CocktailBO>> =
        cocktailRepository.getDrinkById(params.id)

}