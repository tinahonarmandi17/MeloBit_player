package com.example.musicplayer.models.songDetail

data class SongDetail(
    val album: Album,
    val artists: List<ArtistX>,
    val audio: Audio,
    val copyrighted: Boolean,
    val downloadCount: String,
    val duration: Int,
    val hasVideo: Boolean,
    val id: String,
    val image: ImageXXX,
    val localized: Boolean,
    val lyrics: String,
    val releaseDate: String,
    val title: String
)