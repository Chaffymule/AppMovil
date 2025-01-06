package com.example.proyectoofertastrabajo.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

data class AuthRequest(val email: String, val password: String)

interface ConvocatoriaApi {
    @POST("NucleoDigital")
    suspend fun getConvocatorias(
        @Header("Authorization") authToken: String,
        @Body authRequest: AuthRequest
    ): Response<ConvocatoriaResponse>
}