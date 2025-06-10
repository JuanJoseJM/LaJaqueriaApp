package com.example.lajaqueriaapp.network

import android.content.Context
import com.example.lajaqueriaapp.LaJaqueriaApp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:8080"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val prefs = LaJaqueriaApp.context.getSharedPreferences("lajaqueria_prefs", Context.MODE_PRIVATE)
        val token = prefs.getString("access_token", null)

        val originalRequest: Request = chain.request()
        val builder = originalRequest.newBuilder()

        token?.let {
            builder.addHeader("Authorization", "Bearer $it")
        }

        chain.proceed(builder.build())
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}