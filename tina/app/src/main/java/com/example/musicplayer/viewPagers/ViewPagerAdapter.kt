package com.example.musicplayer.viewPagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager,lifecycle: Lifecycle) :
    FragmentStateAdapter(supportFragmentManager,lifecycle) {


    private val fragments: ArrayList<Fragment> = ArrayList()

    fun addFragment (fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int {
        return   fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return  fragments[position]
    }
}