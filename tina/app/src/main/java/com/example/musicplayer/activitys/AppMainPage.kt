package com.example.musicplayer.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.musicplayer.R
import com.example.musicplayer.fragments.OfflinePlaylistFragment
import com.example.musicplayer.fragments.OnlinePlayListFragment
import com.example.musicplayer.fragments.SearchQueryFragment
import com.example.musicplayer.models.Song
import com.example.musicplayer.viewPagers.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class AppMainPage : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private var localSongs : ArrayList<Song> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applcation)

        bindViews();
        setViewPager();
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
        val searchQueryFragment = SearchQueryFragment()
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(localPlayListFragment)
        viewPagerAdapter.addFragment(onlinePlayListFragment)
        viewPagerAdapter.addFragment(searchQueryFragment)
        viewPager2.adapter = viewPagerAdapter
        viewPager2.isUserInputEnabled = false ;

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


    private fun setTabLayout() {


        val localTab = tabLayout.newTab()
        localTab.setIcon(R.drawable.ic_baseline_home_24)
        localTab.text = "LOCAL"
        tabLayout.addTab(localTab)



        val onlineTab = tabLayout.newTab()
        onlineTab.setIcon(R.drawable.internet)
        onlineTab.text = "ONLINE"
        tabLayout.addTab(onlineTab)


        val searchTab = tabLayout.newTab()
        searchTab.setIcon(R.drawable.ic_baseline_search_24)
        searchTab.text = "SEARCH"
        tabLayout.addTab(searchTab)

    }

}