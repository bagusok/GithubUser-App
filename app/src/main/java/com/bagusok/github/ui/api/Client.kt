package com.bagusok.github.ui.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private const val BASE = "https://api.github.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiInstance: ApiServices = retrofit.create(ApiServices::class.java)
}