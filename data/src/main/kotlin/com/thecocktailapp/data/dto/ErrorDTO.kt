package com.thecocktailapp.data.dto

sealed class ErrorDTO {
    data class Basic(val id: Int) : ErrorDTO()
    object Connectivity: ErrorDTO()
    object DataNotFound : ErrorDTO()
    object DeserializingJSON : ErrorDTO()
    object LoadingData : ErrorDTO()
    object LoadingURL : ErrorDTO()
}