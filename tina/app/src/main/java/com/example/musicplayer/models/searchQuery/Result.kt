package com.example.musicplayer.models.searchQuery

data class Result(
    val album: Album,
    val artist: ArtistX,
    val position: Int,
    val song: Song,
    val type: String
)