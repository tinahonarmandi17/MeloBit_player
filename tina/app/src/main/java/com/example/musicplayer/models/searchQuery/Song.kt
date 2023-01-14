package com.example.musicplayer.models.searchQuery

data class Song(
    val album: AlbumX,
    val artists: List<ArtistXXX>,
    val audio: Audio,
    val copyrighted: Boolean,
    val downloadCount: String,
    val duration: Int,
    val hasVideo: Boolean,
    val id: String,
    val image: ImageXXXXXX,
    val localized: Boolean,
    val releaseDate: String,
    val title: String
)