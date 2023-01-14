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
import com.example.musicplayer.activitys.MusicPlayingActivity
import com.example.musicplayer.models.Song
import com.example.musicplayer.services.MusicPlayerService


class OfflinePlayListAdapter(private val activity: Activity) :
    RecyclerView.Adapter<OfflinePlayListAdapter.ViewHolder>() {


    private var songs: ArrayList<Song> = ArrayList() ;
    val TAG = "songs"

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.playlist_music_title);
        val artistText : TextView = itemView.findViewById(R.id.playlist_music_artist)
        val imageView: ImageView = itemView.findViewById(R.id.playlist_music_image_view)
        val playListRoot : ViewGroup = itemView.findViewById(R.id.play_list_root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.playlist_recycler_adapter, parent, false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleText.text = songs[position].title;
        holder.artistText.text = songs[position].artist
        holder.playListRoot.setOnClickListener {
            Intent(activity,MusicPlayingActivity::class.java).also {
                intent ->
                MusicPlayerService._songs.value = songs
                MusicPlayerService._currentPlayingIndex.value = position
                activity.startActivity(intent)
            }
        }
        Glide
            .with(activity)
            .load(songs[position].albumArt)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .placeholder(R.drawable.disk)
            .error(R.drawable.disk)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return songs.size;
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setSongs (songs : ArrayList<Song>) {
        this.songs = songs
        activity.runOnUiThread {
            notifyDataSetChanged()
        }
    }

    fun getSongs () : ArrayList<Song> {
        return songs
    }


}