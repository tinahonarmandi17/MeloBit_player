package com.example.musicplayer.models.top10songs

data class Artist(
    val followersCount: Int,
    val fullName: String,
    val id: String,
    val image: Image,
    val sumSongsDownloadsCount: String,
    val type: String
)