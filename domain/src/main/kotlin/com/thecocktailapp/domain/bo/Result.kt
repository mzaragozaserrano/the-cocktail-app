package com.thecocktailapp.domain.bo

sealed class Result<out T : Any> {
    object Loading : Result<Nothing>()
    sealed class Response<out T : Any> : Result<T>() {
        data class Success<out T : Any>(val data: T) : Response<T>()
        data class Error(val code: ErrorBO) : Response<Nothing>()
    }
}