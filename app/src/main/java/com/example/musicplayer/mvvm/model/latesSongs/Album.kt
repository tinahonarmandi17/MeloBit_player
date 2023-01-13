package com.example.musicplayer.mvvm.model.latesSongs

data class Album(
    val artists: List<Artist>,
    val image: ImageX,
    val name: String,
    val releaseDate: String
)