package com.thecocktailapp.presentation.common.vo

import androidx.annotation.StringRes
import com.thecocktailapp.ui.R

sealed class ErrorVO(@StringRes open val idMessage: Int) {
    data class Basic(val id: Int) : ErrorVO(idMessage = id)
    object Connectivity : ErrorVO(idMessage = R.string.error_connectivity)
    object DataNotFound : ErrorVO(idMessage = R.string.error_data_not_found)
    object DeserializingJSON : ErrorVO(idMessage = R.string.error_deserializing_json)
    object LoadingData : ErrorVO(idMessage = R.string.error_loading_data)
    object LoadingURL : ErrorVO(idMessage = R.string.error_loading_url)
}