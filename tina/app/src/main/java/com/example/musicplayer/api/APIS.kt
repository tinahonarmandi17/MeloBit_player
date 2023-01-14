package com.example.musicplayer.api

import com.example.musicplayer.models.artistSongs.ArtistSongs
import com.example.musicplayer.models.latesSongs.LatestSong
import com.example.musicplayer.models.searchQuery.SearchQuery
import com.example.musicplayer.models.songDetail.SongDetail
import com.example.musicplayer.models.top10songs.Top10songs
import com.example.musicplayer.models.trendingArtists.TrendingArtists
import retrofit2.http.GET
import retrofit2.http.Path

interface APIS {


    @GET("song/new/0/11")
    suspend fun getLatestSongs(): LatestSong

    @GET("artist/trending/0/4")
    suspend fun getTrendingArtists(): TrendingArtists;

    @GET("song/top/day/0/100")
    suspend fun getTop10Songs(): Top10songs;

    @GET("search/query/{query}/0/50")
    suspend fun searchQuery(
        @Path("query") query: String
    ): SearchQuery


    @GET("song/{id}")
    suspend fun getSongDetail(
        @Path("id") id: String
    ): SongDetail


    @GET("artist/songs/top/{id}/0/20")
    suspend fun getArtistTopSongs(
        @Path("id") id: String
    ): ArtistSongs


}