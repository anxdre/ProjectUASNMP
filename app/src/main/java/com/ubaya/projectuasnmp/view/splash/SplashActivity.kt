package com.ubaya.projectuasnmp.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.SharedPrefHelper
import com.ubaya.projectuasnmp.view.auth.login.LoginActivity
import com.ubaya.projectuasnmp.view.home.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val user = SharedPrefHelper(applicationContext).getUser()

        MainScope().launch {
            delay(1800)
            tv_progress.text = "Checking User ..."
            delay(1800)
            if (user.idUser.isNullOrEmpty()){
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            }
            finish()
        }
    }

}