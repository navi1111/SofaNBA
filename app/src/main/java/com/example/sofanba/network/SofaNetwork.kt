package com.example.sofanba.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SofaNetwork {
    private val service:SofaNetworkService
    private val baseUrl="http://academy-2022.dev.sofascore.com"
    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient= OkHttpClient.Builder().addInterceptor(logging)
        val retrofit= Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build()).build()
        service=retrofit.create(SofaNetworkService::class.java)
    }
    fun getservice():SofaNetworkService = service
}