package com.franzandel.dicodingjetpackprosubmission.data.remote

import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

object NetworkService {
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(AppConsts.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}