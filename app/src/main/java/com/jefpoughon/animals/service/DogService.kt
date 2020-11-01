package com.jefpoughon.animals.service

import com.jefpoughon.animals.model.AnimalJson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val DOG_BASE_URL = "https://random.dog"

interface DogService {

    @GET("/woof.json")
    suspend fun getDog(): AnimalJson

    object Creator {
        @JvmStatic
        fun create(): DogService {

            val retrofit = Retrofit.Builder().apply {
                client(OkHttpClient.Builder().build())
                baseUrl(DOG_BASE_URL)
                addConverterFactory(ScalarsConverterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
            }.build()

            return retrofit.create(DogService::class.java)
        }
    }

}