package com.thecocktailapp.domain.usecases

abstract class SyncUseCase<in Params, out Type> where Type : Any {

    abstract operator fun invoke(params: Params): Type

    class None
}

abstract class SyncUseCaseNoParams<out Type> where Type : Any {

    abstract operator fun invoke(): Type

    class None
}