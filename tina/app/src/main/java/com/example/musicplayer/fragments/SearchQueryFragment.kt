package com.example.musicplayer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.api.RetrofitInstance
import com.example.musicplayer.models.searchQuery.ArtistX
import com.example.musicplayer.models.searchQuery.Song
import com.example.musicplayer.recyclerAdapters.SearchArtistsAdapter
import com.example.musicplayer.recyclerAdapters.SearchSongsAdapter
import kotlinx.coroutines.launch


class SearchQueryFragment : Fragment() {

    private lateinit var ediText : EditText
    private lateinit var searchButton : ImageView
    private lateinit var searchArtistsRecyclerView: RecyclerView
    private lateinit var searchSongsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_query, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        setEditTextSubmit ()
        setAdapters ();

    }


    private fun  bindViews () {
        ediText = requireView().findViewById(R.id.search_edittext)
        searchButton = requireView().findViewById(R.id.imageView5)
        searchArtistsRecyclerView = requireView().findViewById(R.id.search_artists_recycler)
        searchSongsRecyclerView = requireView().findViewById(R.id.search_songs_recycler)
    }

    private fun setAdapters () {
        searchArtistsRecyclerView.adapter = SearchArtistsAdapter(requireActivity())
        searchArtistsRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        searchSongsRecyclerView.adapter = SearchSongsAdapter(requireActivity())
        searchSongsRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    private fun setEditTextSubmit() {
        searchButton.setOnClickListener {
            val query = ediText.text.toString()

            lifecycleScope.launch {
                val temp =  RetrofitInstance.searchQuery.searchQuery(query)
                val songs : ArrayList<Song> = ArrayList()
                val artists : ArrayList<ArtistX> = ArrayList()
                for ( result in temp.results ) {
                    if (result.type == "song") {
                        songs.add(result.song)
                    }
                    if (result.type == "artist") {
                        artists.add(result.artist)
                    }
                }
                (searchArtistsRecyclerView.adapter as SearchArtistsAdapter).setData(artists)
                (searchSongsRecyclerView.adapter as SearchSongsAdapter).setData(songs)
            }
        }
    }


}