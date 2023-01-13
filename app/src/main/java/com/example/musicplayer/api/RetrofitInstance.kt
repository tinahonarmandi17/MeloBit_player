package com.example.musicplayer.api

import com.example.musicplayer.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    val latestSongsApi : LatestSongs by lazy {
        retrofit.create(LatestSongs::class.java)
    }

}