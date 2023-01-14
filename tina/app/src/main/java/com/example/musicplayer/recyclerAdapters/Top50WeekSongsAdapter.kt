package com.example.musicplayer.recyclerAdapters

import android.annotation.SuppressLint
import android.app.Activity
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
import com.example.musicplayer.mvvm.model.top10songs.Result
import com.example.musicplayer.mvvm.model.top10songs.Top10songs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class Top50WeekSongsAdapter(private val activity: Activity) :
    RecyclerView.Adapter<Top50WeekSongsAdapter.ViewHolder>() {


    private var top10songs = ArrayList<Result>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView3)
        val titleTextView : TextView = view.findViewById(R.id.textView5)
        val artistTextview : TextView = view.findViewById(R.id.textView7)
        val downloadCount : TextView = view.findViewById(R.id.textView8)
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


        titleTextView.text = top10songs[position].title
        artistTextview.text = top10songs[position].artists[0].fullName
        downloadCount.text = top10songs[position].downloadCount + " Downloads"


        Glide
            .with(activity)
            .load(top10songs[position].image.cover.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return top10songs.size - 1
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(result: ArrayList<Result>) {
        this.top10songs = result
        activity.runOnUiThread {
            notifyDataSetChanged()
        }
    }
}