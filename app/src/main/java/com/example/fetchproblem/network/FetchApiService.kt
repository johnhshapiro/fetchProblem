package com.example.fetchproblem.network

import com.example.fetchproblem.model.Hiring
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object HiringApi {
    val retrofitService : FetchApiService by lazy {
        retrofit.create(FetchApiService::class.java)
    }
}

interface FetchApiService {
    @GET("photos")
    suspend fun getHiring(): List<Hiring>
}