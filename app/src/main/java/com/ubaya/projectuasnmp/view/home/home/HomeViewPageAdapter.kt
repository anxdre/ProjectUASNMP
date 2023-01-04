package com.ubaya.projectuasnmp.view.home.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ubaya.projectuasnmp.view.home.leaderboard.LeaderBoardFragment
import com.ubaya.projectuasnmp.view.home.mycreation.MyCreationFragment
import com.ubaya.projectuasnmp.view.home.settings.SettingsFragment

class HomeViewPageAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                MyCreationFragment()
            }
            2 -> {
                LeaderBoardFragment()
            }
            else -> {
                SettingsFragment()
            }
        }
    }
}