package com.example.musicplayer.recyclerAdapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.musicplayer.R
import com.example.musicplayer.activitys.SongInfoActivity
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.models.latesSongs.ArtistX
import com.example.musicplayer.models.latesSongs.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LatestSongsAdapter(
    private val activity: Activity
) :
    RecyclerView.Adapter<LatestSongsAdapter.ViewHolder>() {

    private var songs = ArrayList<Result>()
    private val TAG = "RETRO"

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image : ImageView = view.findViewById(R.id.lates_songs_image)
        val name : TextView =view.findViewById(R.id.lates_songs_name)
        val artist : TextView  = view.findViewById(R.id.lates_songs_by)
        val downloadCount : TextView = view.findViewById(R.id.lates_songs_downloads_count)
        val latestSongsRoot : ViewGroup = view.findViewById(R.id.latest_songs_item_root)
    }

    init {
        GlobalScope.launch {
            setData(RetrofitInstance.latestSongsApi.getLatestSongs().results)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.latest_songs_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image : ImageView = holder.image
        val nameTextView : TextView = holder.name
        val artistTextView = holder.artist
        val downloadCountTextView = holder.downloadCount
        val root = holder.latestSongsRoot




        // data
        val imageUri = songs.get(position)?.image?.cover?.url
        val trackTitle = songs.get(position)?.title
        val artists = songs.get(position)?.artists




        for ( artist : ArtistX in artists!! ) {
            if (artistTextView.text == "") {
                artistTextView.text = artist.fullName
            } else {
                artistTextView.text = "${artistTextView.text} && ${artist.fullName}"
            }
        }

        nameTextView.text =  trackTitle
        downloadCountTextView.text = songs.get(position)?.downloadCount


        Glide
            .with(activity)
            .load(imageUri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image)

        root.setOnClickListener {


            activity.startActivity(Intent(activity,SongInfoActivity::class.java))
            SongInfoActivity.songID = songs[position].id
        }

    }

    override fun getItemCount(): Int {
       return  songs.size-1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(songs: List<Result>) {
        this.songs = songs as ArrayList<Result>
        activity.runOnUiThread {
            notifyDataSetChanged()
        }
    }


}