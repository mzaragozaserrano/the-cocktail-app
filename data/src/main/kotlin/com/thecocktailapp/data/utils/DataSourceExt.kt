package com.thecocktailapp.data.utils

import com.thecocktailapp.data.dto.ResultDTO
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
fun <D, E> CancellableContinuation<ResultDTO<D>>.onError(error: E) {
    resume(ResultDTO.Error(error), null)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <D> CancellableContinuation<ResultDTO<D>>.onSuccess(data: D) {
    resume(ResultDTO.Response(data), null)
}