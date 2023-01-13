package com.example.musicplayer.splash

import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.example.musicplayer.PlayListRecyclerAdapter
import com.example.musicplayer.R
import com.example.musicplayer.fragments.OfflinePlaylistFragment
import com.example.musicplayer.fragments.OnlinePlayListFragment
import com.example.musicplayer.mvvm.model.Song
import com.example.musicplayer.viewPagers.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class AppMainPage : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applcation)

        bindViews();
        setViewPager();
        setOfflinePlayList()
        setTabLayout()
        setTabLayoutViewPager();

    }

    private fun bindViews() {
        tabLayout = findViewById(R.id.playlist_tabl_layout)
        viewPager2 = findViewById(R.id.playlists_view_pager)
    }

    private fun setViewPager() {
        val onlinePlayListFragment = OnlinePlayListFragment()
        val localPlayListFragment = OfflinePlaylistFragment()
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(localPlayListFragment)
        viewPagerAdapter.addFragment(onlinePlayListFragment)
        viewPager2.adapter = viewPagerAdapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

    }

    private fun setTabLayoutViewPager() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
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

        val cursor = managedQuery(
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

    private fun setOfflinePlayList() {
        val playListRecycler: RecyclerView = findViewById(R.id.playlist);
        playListRecycler.adapter = PlayListRecyclerAdapter(findAllSongs(), this);
        playListRecycler.layoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
    }

    private fun setTabLayout() {
        tabLayout = findViewById(R.id.playlist_tabl_layout)
        val localTab = tabLayout.newTab()
        localTab.setIcon(R.drawable.ic_baseline_home_24)
        localTab.text = "LOCAL"
        tabLayout.addTab(localTab)
        val onlineTab = tabLayout.newTab()
        onlineTab.setIcon(R.drawable.intermet)
        onlineTab.text = "ONLINE"
        tabLayout.addTab(onlineTab)


    }

}