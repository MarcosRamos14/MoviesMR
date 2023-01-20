package com.marcos.moviesmr.presentation.extensions

import com.marcos.moviesmr.core.usecase.base.ResultStatus
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<ResultStatus<T>>.watchStatus(
    loading: suspend () -> Unit = {},
    success: suspend (data: T) -> Unit,
    error: suspend () -> Unit = {}
) {
    collect { status ->
        when (status) {
            ResultStatus.Loading -> loading()
            is ResultStatus.Success -> success.invoke(status.data)
            is ResultStatus.Error -> error()
        }
    }
}