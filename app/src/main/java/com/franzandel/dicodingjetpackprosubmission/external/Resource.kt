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
        fun <T> success(data: T?): Resource<T> =
            Resource(
                Status.SUCCESS,
                data,
                null
            )

        fun <T> error(errorCode: ErrorCodeData?): Resource<T> =
            Resource(
                Status.ERROR,
                null,
                errorCode
            )

        fun <T> error(data: T?, errorCode: ErrorCodeData?): Resource<T> =
            Resource(
                Status.ERROR,
                data,
                errorCode
            )
    }

    enum class Status {
        SUCCESS,
        ERROR
    }
}