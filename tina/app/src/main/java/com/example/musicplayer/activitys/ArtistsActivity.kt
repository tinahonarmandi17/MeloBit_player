package com.example.musicplayer.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.recyclerAdapters.ArtistSongsAdapter

class ArtistsActivity : AppCompatActivity() {


    companion object {
        var artistID = ""
    }

    private lateinit var artistSongsRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)


        artistSongsRecyclerView = findViewById(R.id.artist_songs_recycler)
        val adapter = ArtistSongsAdapter(this)
        artistSongsRecyclerView.adapter = adapter
        artistSongsRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launchWhenCreated {
            val songs = RetrofitInstance.artistSongsApi.getArtistTopSongs(artistID).results
            adapter.setData(songs)
        }
    }


    private suspend fun findArtist() {
    }


}