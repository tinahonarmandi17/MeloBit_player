package com.example.musicplayer.recyclerAdapters

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.musicplayer.R
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.mvvm.model.latesSongs.ArtistX
import com.example.musicplayer.mvvm.model.latesSongs.LatestSong
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LatestSongsAdapter(
    private val activity: Activity
) :
    RecyclerView.Adapter<LatestSongsAdapter.ViewHolder>() {

    private var latestSong: LatestSong? = null;
    private val TAG = "RETRO"

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image : ImageView = view.findViewById(R.id.lates_songs_image)
        val name : TextView =view.findViewById(R.id.lates_songs_name)
        val artist : TextView  = view.findViewById(R.id.lates_songs_by)
    }

    init {
        GlobalScope.launch {
            setData(RetrofitInstance.latestSongsApi.getLatestSongs())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.latest_songs_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image : ImageView = holder.image
        var nameTextView : TextView = holder.name
        val artistTextView = holder.artist


        val imageUri = latestSong?.results?.get(position)?.image?.cover?.url

        val trackTitle = latestSong?.results?.get(position)?.title

        val artists = latestSong?.results?.get(position)?.artists

        for ( artist : ArtistX in artists!! ) {
            if (artistTextView.text == "") {
                artistTextView.text = artist.fullName
            } else {
                artistTextView.text = "${artistTextView.text} && ${artist.fullName}"
            }
        }

        nameTextView.text =  trackTitle



        Glide
            .with(activity)
            .load(imageUri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image)

    }

    override fun getItemCount(): Int {
        return if (latestSong == null) {
            0
        } else {
            latestSong!!.results.size
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(latestSong: LatestSong) {
        this.latestSong = latestSong
        activity.runOnUiThread {
            notifyDataSetChanged()
        }
    }


}