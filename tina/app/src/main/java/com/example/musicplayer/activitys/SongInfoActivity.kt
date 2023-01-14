package com.example.musicplayer.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.musicplayer.R
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.utils.FileDownloader

class SongInfoActivity() : AppCompatActivity() {

    companion object{
        var songID : String = ""
    }

    private lateinit var imageView: ImageView
    private lateinit var title: TextView
    private lateinit var artists: TextView
    private lateinit var downloadsCount: TextView
    private lateinit var downloadButton : Button
    private lateinit var lyricsTextview : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_info)
        imageView = findViewById(R.id.imageView6)
        title = findViewById(R.id.textView13)
        artists = findViewById(R.id.textView14)
        downloadsCount = findViewById(R.id.textView15)
        downloadButton = findViewById(R.id.button)
        lyricsTextview = findViewById(R.id.textView16)
        lifecycleScope.launchWhenCreated {
            val song = RetrofitInstance.songDetailApi.getSongDetail(songID)
            this@SongInfoActivity.runOnUiThread {
                title.text = song.title
                artists.text = song.artists[0].fullName
                downloadsCount.text = song.downloadCount
                lyricsTextview.text = song.lyrics

                Glide
                    .with(this@SongInfoActivity)
                    .load(song.image.cover.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)


            }
            downloadButton.setOnClickListener {
                FileDownloader.setInitContext(this@SongInfoActivity)
                val downloadUri = song.audio.high.url
                val fileName = song.title
                FileDownloader.download(downloadUri, fileName, ".mp3")
            }

        }

    }
}