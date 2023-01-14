package com.example.musicplayer.models.artistSongs

data class Album(
    val artists: List<Artist>,
    val image: ImageX,
    val name: String,
    val releaseDate: String
)