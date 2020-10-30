package com.jefpoughon.animals.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val DOG_BASE_URL = "https://random.dog"

interface DogService {

    @GET("/woof.json")
    suspend fun getDog(): String

    object Creator {
        @JvmStatic
        fun create(): DogService {

            val retrofit = Retrofit.Builder().apply {
                client(OkHttpClient.Builder().build())
                baseUrl(DOG_BASE_URL)
                addConverterFactory(ScalarsConverterFactory.create())
            }.build()

            return retrofit.create(DogService::class.java)
        }
    }

}