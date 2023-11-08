package com.thecocktailapp.domain.usecases

import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.bo.Result
import com.thecocktailapp.domain.repositories.NetworkRepository
import com.thecocktailapp.domain.utils.toFlowResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@Suppress("UNCHECKED_CAST")
abstract class FlowUseCase<in Params, out Type>(private val networkRepository: NetworkRepository) where Type : Any {
    abstract suspend fun run(params: Params): Flow<Type>

    suspend operator fun invoke(params: Params): Flow<Type> =
        if (networkRepository.isConnected()) {
            run(params).flowOn(Dispatchers.IO)
        } else {
            (Result.Response.Error(ErrorBO.Connectivity) as Type).toFlowResult()
        }

}

@Suppress("UNCHECKED_CAST")
abstract class FlowUseCaseNoParams<out Type>(private val networkRepository: NetworkRepository) where Type : Any {
    abstract suspend fun run(): Flow<Type>

    suspend operator fun invoke(): Flow<Type> =
        if (networkRepository.isConnected()) {
            run().flowOn(Dispatchers.IO)
        } else {
            (Result.Response.Error(ErrorBO.Connectivity) as Type).toFlowResult()
        }

}