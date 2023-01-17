package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.example.myapplication.R;
import com.example.myapplication.fragments.OnlinePlayListFragment;
import com.example.myapplication.fragments.SearchQueryFragment;
import com.example.myapplication.viewPagers.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AppMainPage extends AppCompatActivity {

   private TabLayout tabLayout;
   private ViewPager2 viewPager2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bindViews();
        setViewPagers();
        setTabLayout();
        setTabLayoutViewPager();

    }


    private void bindViews() {
        tabLayout = findViewById(R.id.playlist_tabl_layout);
        viewPager2 = findViewById(R.id.playlists_view_pager);
    }

    private void  setViewPagers () {
        OnlinePlayListFragment onlinePlayListFragment = new OnlinePlayListFragment();
        SearchQueryFragment searchQueryFragment = new SearchQueryFragment();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPagerAdapter.addFragment(onlinePlayListFragment);
        viewPagerAdapter.addFragment(searchQueryFragment);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setUserInputEnabled(false);
    }

    private void  setTabLayout() {
        TabLayout.Tab onlineTab = tabLayout.newTab();
        onlineTab.setIcon(R.drawable.internet);
        onlineTab.setText( "ONLINE") ;
        tabLayout.addTab(onlineTab);

        TabLayout.Tab searchTab = tabLayout.newTab();
        searchTab.setIcon(R.drawable.ic_baseline_search_24);
        searchTab.setText("SEARCH") ;
        tabLayout.addTab(searchTab);

    }

    private void setTabLayoutViewPager() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}