package com.example.superproyectocomicon.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroRFC {
    companion object {
        private const val URL_BASE = "https://y-mariocanedo.vercel.app/"

        fun getRetrofitHero(): HeroApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(HeroApi::class.java)
        }
    }
}