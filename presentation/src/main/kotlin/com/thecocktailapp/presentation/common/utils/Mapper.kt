package com.thecocktailapp.presentation.common.utils

import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.presentation.common.vo.ErrorVO

fun ErrorBO.transform(): ErrorVO = when (this) {
    is ErrorBO.Basic -> ErrorVO.Basic(id = id)
    is ErrorBO.Connectivity -> ErrorVO.Connectivity
    is ErrorBO.DataNotFound -> ErrorVO.DataNotFound
    is ErrorBO.DeserializingJSON -> ErrorVO.DeserializingJSON
    is ErrorBO.LoadingData -> ErrorVO.LoadingData
    is ErrorBO.LoadingURL -> ErrorVO.LoadingURL
}