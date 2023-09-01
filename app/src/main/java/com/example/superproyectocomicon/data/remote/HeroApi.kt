package com.example.superproyectocomicon.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("superheroes")
    suspend fun getProductsData(): Response<List<HeroData>>

    @GET("superheroes/{id}")
    suspend fun getDetailsData(@Path("id")id:Int): Response<HeroDetailsData>
}