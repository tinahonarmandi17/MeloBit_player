package com.example.musicplayer.models.top10songs

data class Album(
    val artists: List<Artist>,
    val id: String,
    val image: ImageX,
    val name: String,
    val releaseDate: String
)