package com.example.musicplayer.recyclerAdapters

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
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer.R
import com.example.musicplayer.activitys.ArtistsActivity
import com.example.musicplayer.models.searchQuery.ArtistX

class SearchArtistsAdapter(private val activity: Activity) :
    RecyclerView.Adapter<SearchArtistsAdapter.ViewHolder>() {


    private var artists: ArrayList<ArtistX> = ArrayList()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.findViewById(R.id.imageView4)
        val artistName : TextView = view.findViewById(R.id.textView9)
        val artistFollower : TextView = view.findViewById(R.id.textView10)
        val root : ViewGroup = view.findViewById(R.id.artist_root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.imageView
        val artistName = holder.artistName
        val artistsFollowers = holder.artistFollower



        artistName.text = artists[position].fullName
        artistsFollowers.text = artists[position].followersCount.toString() + " Followers"


        Glide
            .with(activity)
            .load(artists[position].image.cover.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)

        holder.root.setOnClickListener {
            activity.startActivity(Intent(activity,ArtistsActivity::class.java))
            ArtistsActivity.artistID = artists[position].id
        }

    }

    override fun getItemCount(): Int {
        return artists.size-1
    }


    fun setData(data: ArrayList<ArtistX>) {
        this.artists = data
        activity.runOnUiThread {
            notifyDataSetChanged()
        }
    }

}