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
import com.example.musicplayer.models.top10songs.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Top10SongsAdapter(private val activity: Activity) :
    RecyclerView.Adapter<Top10SongsAdapter.ViewHolder>() {


    private var songs = ArrayList<Result>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView3)
        val titleTextView : TextView = view.findViewById(R.id.textView5)
        val artistTextview : TextView = view.findViewById(R.id.textView7)
        val downloadCount : TextView = view.findViewById(R.id.textView8)
        val root : ViewGroup = view.findViewById(R.id.song_root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.song, parent, false)
        return ViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        GlobalScope.launch {
            val results = RetrofitInstance.top10Songs.getTop10Songs().results
            setData(results as ArrayList<Result>)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView: ImageView = holder.imageView
        val titleTextView : TextView = holder.titleTextView
        val artistTextview : TextView = holder.artistTextview
        val downloadCount : TextView = holder.downloadCount


        titleTextView.text = songs[position].title
        artistTextview.text = songs[position].artists[0].fullName
        downloadCount.text = songs[position].downloadCount + " Downloads"


        Glide
            .with(activity)
            .load(songs[position].image.cover.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)

        holder.root.setOnClickListener {
            activity.startActivity(Intent(activity,SongInfoActivity::class.java))
            SongInfoActivity.songID = songs[position].id
        }
    }

    override fun getItemCount(): Int {
        return songs.size - 1
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(result: ArrayList<Result>) {
        this.songs = result
        activity.runOnUiThread {
            notifyDataSetChanged()
        }
    }
}