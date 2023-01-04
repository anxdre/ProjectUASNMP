package com.ubaya.projectuasnmp.view.home

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.android.volley.toolbox.Volley
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.view.home.home.HomeViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    private val volley by lazy { Volley() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpAdapter = HomeViewPageAdapter(this)
        vp_main.adapter = vpAdapter

        vp_main.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        bn_main.selectedItemId = R.id.itemHome
                    }
                    1 -> {
                        bn_main.selectedItemId = R.id.itemMyCreation
                    }
                    2 -> {
                        bn_main.selectedItemId = R.id.itemLeaderboard
                    }
                    3 -> {
                        bn_main.selectedItemId = R.id.itemSettings
                    }
                }
            }
        })

        bn_main.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    vp_main.setCurrentItem(0, true)
                }
                R.id.itemMyCreation -> {
                    vp_main.setCurrentItem(1, true)
                }
                R.id.itemLeaderboard -> {
                    vp_main.setCurrentItem(2, true)
                }
                R.id.itemSettings -> {
                    vp_main.setCurrentItem(3, true)
                }
            }
            true
        }

        tb_main.setNavigationOnClickListener { dw_main.openDrawer(GravityCompat.START) }

        nav_view.setNavigationItemSelectedListener {
            changePagePosition(it.itemId)
            dw_main.closeDrawers()
            true
        }
    }

    fun changePagePosition(menuId: Int) {
        bn_main.selectedItemId = menuId
    }
}