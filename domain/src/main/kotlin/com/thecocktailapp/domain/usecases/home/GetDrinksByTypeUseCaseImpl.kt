package com.thecocktailapp.domain.usecases.home

import androidx.annotation.StringRes
import com.thecocktailapp.core.domain.repositories.NetworkRepository
import com.thecocktailapp.core.domain.usecases.FlowUseCase
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetDrinksByType = FlowUseCase<GetDrinksByTypeUseCaseImpl.Params, @JvmSuppressWildcards Result<CocktailBO>, ErrorBO>

class GetDrinksByTypeUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetDrinksByType(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    data class Params(@StringRes val dbId: Int)

    override suspend fun run(params: Params): Flow<Result<CocktailBO>> =
        cocktailRepository.getDrinksByType(dbId = params.dbId)

}