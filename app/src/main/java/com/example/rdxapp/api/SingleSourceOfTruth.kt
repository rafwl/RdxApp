package com.example.rdxapp.api

import com.example.rdxapp.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> resultFlow(
    networkCall: suspend () -> Result<T>,
    saveCallResult: suspend (T) -> Unit
): Flow<Result<T>> =
    flow {
        emit(Result.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            responseStatus.data?.let {  responseData ->
                saveCallResult(responseData)
                emit(
                    Result.success(
                        responseData
                    )
                )
            }
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(
                Result.error(
                    responseStatus.data
                )
            )
        }
    }.flowOn(Dispatchers.IO)