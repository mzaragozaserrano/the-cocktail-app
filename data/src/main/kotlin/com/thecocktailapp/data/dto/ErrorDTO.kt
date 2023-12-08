package com.thecocktailapp.data.dto

sealed class ErrorDTO {
    object Connectivity: ErrorDTO()
    object DataNotFound : ErrorDTO()
    object DeserializingJSON : ErrorDTO()
    data class Generic(val id: Int) : ErrorDTO()
    object LoadingData : ErrorDTO()
    object LoadingURL : ErrorDTO()
}