package com.thecocktailapp.presentation.vo

import androidx.annotation.StringRes
import com.thecocktailapp.ui.R

sealed class ErrorVO(@StringRes open val messageId: Int) {
    object Connectivity : ErrorVO(messageId = R.string.error_connectivity)
    object DataNotFound : ErrorVO(messageId = R.string.error_data_not_found)
    object DeserializingJSON : ErrorVO(messageId = R.string.error_deserializing_json)
    data class Generic(val id: Int) : ErrorVO(messageId = id)
    object LoadingData : ErrorVO(messageId = R.string.error_loading_data)
    object LoadingURL : ErrorVO(messageId = R.string.error_loading_url)
}