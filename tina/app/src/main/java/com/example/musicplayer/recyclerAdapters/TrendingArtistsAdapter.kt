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
import com.example.musicplayer.activitys.ArtistsActivity
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.models.trendingArtists.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrendingArtistsAdapter(private val activity: Activity) :
    RecyclerView.Adapter<TrendingArtistsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView4)
        val artistName: TextView = view.findViewById(R.id.textView9)
        val artistFollower: TextView = view.findViewById(R.id.textView10)
        val artistRoot : ViewGroup = view.findViewById(R.id.artist_root)
    }


    private var artists = ArrayList<Result>()


    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)


    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        GlobalScope.launch {
            val temp =
                RetrofitInstance.trendingArtistsApi.getTrendingArtists().results as ArrayList<Result>
            setData(temp)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.artist, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.imageView
        val artistName = holder.artistName
        val artistsFollowers = holder.artistFollower



        artistName.text = artists[position].fullName
        artistsFollowers.text = "${artists[position].followersCount} Followers"


        Glide
            .with(activity)
            .load(artists[position].image.cover.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)


        holder.artistRoot.setOnClickListener {
            activity.startActivity(Intent(activity,ArtistsActivity::class.java))
            ArtistsActivity.artistID  = artists[position].id
        }

    }

    override fun getItemCount(): Int {
        return artists.size - 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(artists: ArrayList<Result>) {
        activity.runOnUiThread {
            this.artists = artists
            notifyDataSetChanged()
        }
    }
}