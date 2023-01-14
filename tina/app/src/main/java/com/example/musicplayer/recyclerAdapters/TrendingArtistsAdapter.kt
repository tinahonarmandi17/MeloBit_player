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
import com.example.musicplayer.mvvm.model.trendingArtists.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class TrendingArtistsAdapter(private val activity: Activity) : RecyclerView.Adapter<TrendingArtistsAdapter.ViewHolder>() {




    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.findViewById(R.id.imageView4)
        val artistName : TextView = view.findViewById(R.id.textView9)
        val artistFollower : TextView  = view.findViewById(R.id.textView10)
    }


    private var artists = ArrayList<Result>()


    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)


    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        GlobalScope.launch {
            val temp = RetrofitInstance.trendingArtistsApi.getTrendingArtists().results as ArrayList<Result>
            setData(temp)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.artist,parent,false)
        return  ViewHolder(view)
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
            .into(imageView)


    }

    override fun getItemCount(): Int {
       return artists.size-1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(artists : ArrayList<Result>) {
        activity.runOnUiThread {
            this.artists = artists
            notifyDataSetChanged()
        }
    }
}