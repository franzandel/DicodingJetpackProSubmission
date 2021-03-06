package com.franzandel.dicodingjetpackprosubmission.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.google.gson.Gson
import okhttp3.ResponseBody

/**
 * Created by Franz Andel on 03/03/21.
 * Android Engineer
 */

abstract class BaseNetworkRepository(protected val gson: Gson) {

    protected suspend fun <T> withTryCatch(
        networkResponse: MutableLiveData<Resource<T>>,
        networkCall: suspend () -> Unit
    ): LiveData<Resource<T>> {

        try {
            networkCall.invoke()
        } catch (e: Exception) {
            networkResponse.postValue(
                Resource.error(
                    null,
                    ErrorCodeData(-1, "Tidak ada koneksi internet")
                )
            )
        }

        return networkResponse
    }

    protected fun <T> getParsedErrorModel(errorBody: ResponseBody?, modelClass: Class<T>): T {
        return gson.fromJson(errorBody?.charStream(), modelClass)
    }
}