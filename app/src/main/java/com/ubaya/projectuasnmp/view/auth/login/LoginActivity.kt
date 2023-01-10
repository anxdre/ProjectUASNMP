package com.ubaya.projectuasnmp.view.auth.login

import User
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.SharedPrefHelper
import com.ubaya.projectuasnmp.data.api.repositories.AuthRepository
import com.ubaya.projectuasnmp.util.showLoadingUi
import com.ubaya.projectuasnmp.util.showSortToast
import com.ubaya.projectuasnmp.view.auth.register.RegisterActivity
import com.ubaya.projectuasnmp.view.home.MainActivity
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val volley by lazy { Volley.newRequestQueue(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn.setOnClickListener {

            if (validateField()) {
                fetchUserData(
                    et_username_login.text.toString(),
                    et_password_login.text.toString()
                )
            }
        }

        btnCreateAcc.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateField(): Boolean {
        val validateEmpty = nonEmptyList(
            et_username_login,
            et_password_login
        ) { view, _ ->
            view.error = "Field cannot be empty"
            view.requestFocus()
        }
        return validateEmpty
    }

    private fun fetchUserData(username: String, password: String) {
        val loading = showLoadingUi(this)
        loading.show()
        volley.add(AuthRepository().login(
            username = username,
            password = password,
            onSuccess = {
                loading.dismiss()
                val result = Gson().fromJson(it, User::class.java).data
                if (result != null) {
                    SharedPrefHelper(applicationContext).storeUser(result)
                    Toast.makeText(
                        applicationContext,
                        "Welcome ${result.username}",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    showSortToast(applicationContext, "Something error occurred")
                }

            },
            onFailed = {
                loading.dismiss()
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        )
    }
}