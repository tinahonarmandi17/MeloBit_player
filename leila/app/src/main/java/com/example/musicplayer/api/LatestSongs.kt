package com.example.musicplayer.api

import com.example.musicplayer.mvvm.model.latesSongs.LatestSong
import retrofit2.http.GET

interface LatestSongs {


    @GET("song/new/0/11")
    suspend fun getLatestSongs() : LatestSong
}