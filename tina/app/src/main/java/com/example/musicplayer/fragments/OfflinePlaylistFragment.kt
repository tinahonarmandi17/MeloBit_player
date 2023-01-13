package com.example.musicplayer.fragments

import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.recyclerAdapters.OfflinePlayListAdapter
import com.example.musicplayer.R
import com.example.musicplayer.mvvm.model.Song


class OfflinePlaylistFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_offline_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOfflinePlayList()
    }

    private fun setOfflinePlayList() {
        val playListRecycler: RecyclerView = requireView().findViewById(R.id.local_playlist);
        playListRecycler.adapter = OfflinePlayListAdapter(findAllSongs(), requireContext());
        playListRecycler.layoutManager =
            GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false);
    }

    private fun findAllSongs(): ArrayList<Song> {
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM
        )

        val cursor = requireActivity().managedQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        val songs: ArrayList<Song> = ArrayList()
        while (cursor.moveToNext()) {
            val song: Song = Song.Builder()
                .id(cursor.getString(0))
                .artist(cursor.getString(1))
                .title(cursor.getString(2))
                .data(cursor.getString(3))
                .displayName(cursor.getString(4))
                .duration(cursor.getString(5))
                .album(cursor.getString(6))
                .build()
            songs.add(song)
        }
        return songs
    }


}