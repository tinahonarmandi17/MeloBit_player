package com.example.musicplayer.models.searchQuery

data class SearchQuery(
    val results: List<Result>,
    val total: Int
)