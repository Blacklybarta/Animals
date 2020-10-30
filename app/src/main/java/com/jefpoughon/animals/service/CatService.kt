package com.jefpoughon.animals.service

import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val CAT_BASE_URL = "https://aws.random.cat"

interface CatService  {

    @GET("/meow")
    suspend fun getCat(): String

    object Creator {
        @JvmStatic
        fun create(): CatService {

            val retrofit = Retrofit.Builder().apply {
                client(OkHttpClient.Builder().build())
                baseUrl(CAT_BASE_URL)
                addConverterFactory(ScalarsConverterFactory.create())
            }.build()

            return retrofit.create(CatService::class.java)
        }
    }

}