package com.example.lajaqueriaapp.network

import com.example.lajaqueriaapp.data.model.LoginRequest
import com.example.lajaqueriaapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}

