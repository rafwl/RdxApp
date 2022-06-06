package com.example.rdxapp.data

data class Result<out T>(val status: Status, val data: T?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(data: T? = null): Result<T> {
            return Result(
                Status.ERROR,
                data
            )
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(
                Status.LOADING,
                data
            )
        }
    }
}