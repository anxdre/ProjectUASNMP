package com.ubaya.projectuasnmp.view.home

import User
import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.android.volley.toolbox.Volley
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.SharedPrefHelper
import com.ubaya.projectuasnmp.util.fetchImageFromNetwork
import com.ubaya.projectuasnmp.view.auth.login.LoginActivity
import com.ubaya.projectuasnmp.view.home.home.HomeViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_header_layout.view.*

class MainActivity : FragmentActivity() {
    val volley by lazy { Volley.newRequestQueue(this) }
    lateinit var userData: User.Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userData = SharedPrefHelper(applicationContext).getUser()
        nav_view.getHeaderView(0).tv_userName.text = "Welcome, ${userData.username}"
        nav_view.getHeaderView(0).fab_logout_header.setOnClickListener {
            SharedPrefHelper(applicationContext).deleteUser()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

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


    override fun onStop() {
        super.onStop()
        volley.cancelAll("api")
    }
}