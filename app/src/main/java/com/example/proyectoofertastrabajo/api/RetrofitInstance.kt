package com.example.proyectoofertastrabajo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://comedatos.qroo.gob.mx/api/"

    val api: ConvocatoriaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConvocatoriaApi::class.java)
    }
}