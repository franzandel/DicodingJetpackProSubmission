package com.franzandel.dicodingjetpackprosubmission.external

/**
 * Created by Franz Andel on 03/03/21.
 * Android Engineer
 */

data class Resource<T>(
    val status: Status,
    var data: T?,
    val errorCodeData: ErrorCodeData?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(errorCode: ErrorCodeData?): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                errorCode
            )
        }

        fun <T> error(data: T?, errorCode: ErrorCodeData?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                errorCode
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}