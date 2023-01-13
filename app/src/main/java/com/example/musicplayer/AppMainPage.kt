package com.example.musicplayer

import com.example.musicplayer.mvvm.model.Song
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class AppMainPage : AppCompatActivity() {

    val TAG = "songs"
    val test = " sog";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applcation)


        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM
        )

        val cursor = managedQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        val songs: ArrayList<Song> = ArrayList()
        while (cursor.moveToNext()) {
            val song: Song = Song.Builder()
                .id(cursor.getString(0))
                .artist(cursor.getString(1))
                .title(cursor.getString(2))
                .data(cursor.getString(3))
                .displayName(cursor.getString(4))
                .duration(cursor.getString(5))
                .album(cursor.getString(6))
                .build()
            songs.add(song)
        }

        val playListRecycler: RecyclerView = findViewById(R.id.playlist);
        playListRecycler.adapter = PlayListRecyclerAdapter(songs, this);
        playListRecycler.layoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
    }
}