package com.ubaya.projectuasnmp.view.auth.register

import User
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.api.repositories.AuthRepository
import com.ubaya.projectuasnmp.util.showLoadingUi
import com.ubaya.projectuasnmp.util.showSortToast
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import kotlinx.android.synthetic.main.activity_registration.*

class RegisterActivity : AppCompatActivity() {
    private val volley by lazy { Volley.newRequestQueue(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btn_back_regis.setOnClickListener { finish() }
        btn_regis.setOnClickListener {
            val loading = showLoadingUi(this)
            loading.show()
            if (validateField()) {
                fetchUserData()
            }
            loading.dismiss()
        }

    }

    private fun validateField(): Boolean {
        val validateEmpty = nonEmptyList(
            et_username_regis,
            et_password_regis,
            et_repass_regis
        ) { view, _ ->
            view.error = "Field cannot be empty"
            view.requestFocus()
        }
        val validatePassword = et_password_regis.text.toString() == et_repass_regis.text.toString()

        if (!validatePassword){
            et_repass_regis.requestFocus()
            et_repass_regis.error = "Password not match"
        }

        return validateEmpty && validatePassword
    }

    private fun fetchUserData() {
        val loading = showLoadingUi(this)
        loading.show()
        val user = User.Data(
            "",
            null,
            "",
            "",
            "0",
            "",
            et_password_regis.text.toString(),
            et_username_regis.text.toString()
        )
        volley.add(AuthRepository().register(
            user = user,
            onSuccess = {
                loading.dismiss()
                val result = Gson().fromJson(it, User::class.java)
                showSortToast(applicationContext, "${result.msg} login to use the App")
                finish()
            },
            onFailed = {
                loading.dismiss()
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        )
    }
}
