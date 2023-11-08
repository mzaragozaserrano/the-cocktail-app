package com.thecocktailapp.data.dto

sealed class ResultData<out T : Any> {

    data class Response<out T : Any>(val data: T) : ResultData<T>()

    data class Error(val code: ErrorDTO) : ResultData<Nothing>()

}