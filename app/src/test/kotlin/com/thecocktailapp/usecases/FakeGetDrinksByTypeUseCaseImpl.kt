package com.thecocktailapp.usecases

import com.thecocktailapp.core.domain.repositories.NetworkRepository
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import com.thecocktailapp.domain.usecases.home.GetDrinksByType
import com.thecocktailapp.domain.usecases.home.GetDrinksByTypeUseCaseImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeGetDrinksByTypeUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetDrinksByType(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    private var alcoholic: String = "Alcoholic"

    fun setAlcoholicType(alcoholic: String) {
        this.alcoholic = alcoholic
    }

    override suspend fun run(params: GetDrinksByTypeUseCaseImpl.Params): Flow<Result<CocktailBO>> =
        cocktailRepository.getDrinksByType(alcoholic = alcoholic)

}