package com.example.musicplayer.api

import com.example.musicplayer.mvvm.model.latesSongs.LatestSong
import com.example.musicplayer.mvvm.model.top10songs.Top10songs
import com.example.musicplayer.mvvm.model.trendingArtists.TrendingArtists
import retrofit2.http.GET

interface APIS {


    @GET("song/new/0/11")
    suspend fun getLatestSongs() : LatestSong
    
    @GET("artist/trending/0/4")
    suspend fun getTrendingArtists() : TrendingArtists ;

    @GET("song/top/day/0/100")
    suspend fun getTop10Songs () : Top10songs;

}