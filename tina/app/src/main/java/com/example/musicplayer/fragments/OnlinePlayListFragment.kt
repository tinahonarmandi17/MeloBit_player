package com.example.musicplayer.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.mvvm.model.latesSongs.LatestSong
import com.example.musicplayer.recyclerAdapters.LatestSongsAdapter
import com.example.musicplayer.recyclerAdapters.TrendingArtistsAdapter


class OnlinePlayListFragment : Fragment() {


    companion object {
        private const val  TAG = "RETRO"
    }

    private lateinit var latestSongs : MutableLiveData<LatestSong>;

    private lateinit var latestPlayListRecyclerView: RecyclerView
    private lateinit var trendingArtistRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_online_play_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestPlayListRecyclerView = view.findViewById(R.id.latest_songs_playlist)
        trendingArtistRecyclerView = view.findViewById(R.id.trending_artists_recycler)



        latestPlayListRecyclerView.adapter = LatestSongsAdapter(requireActivity())
        latestPlayListRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        trendingArtistRecyclerView.adapter = TrendingArtistsAdapter(requireActivity())
        trendingArtistRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


    }
    



    



}