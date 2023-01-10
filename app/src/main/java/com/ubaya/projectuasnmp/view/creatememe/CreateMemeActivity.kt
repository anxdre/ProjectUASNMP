package com.ubaya.projectuasnmp.view.creatememe

import Post
import User
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.SharedPrefHelper
import com.ubaya.projectuasnmp.data.api.repositories.PostRepository
import com.ubaya.projectuasnmp.util.fetchImageFromNetwork
import com.ubaya.projectuasnmp.util.showLoadingUi
import com.ubaya.projectuasnmp.util.showSortToast
import com.ubaya.projectuasnmp.view.home.MainActivity
import kotlinx.android.synthetic.main.activity_create_new_meme.*

class CreateMemeActivity : AppCompatActivity() {
    private val volley by lazy { Volley.newRequestQueue(this) }
    private lateinit var userData: User.Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_meme)
        userData = SharedPrefHelper(applicationContext).getUser()
        btn_submit_edit.setOnClickListener {
            try {
                if (et_img_url_edit.text!!.isNotEmpty()) {
                    submitMeme(userData.idUser!!)
                }
            } catch (ex: Exception) {
                showSortToast(this, ex.message.toString())
            }
        }

        et_img_url_edit.doOnTextChanged { text, start, before, count ->
            try{
                fetchImageFromNetwork(text.toString(), imagePreview)
            }catch (ex:Exception){
                showSortToast(this,ex.message.toString())
            }

        }

        et_top_text_edit.doOnTextChanged { text, start, before, count ->
            tv_title_top_edit.text = text
        }

        et_bottom_text_edit.doOnTextChanged { text, start, before, count ->
            tv_title_bottom_edit.text = text
        }

        btnBack.setOnClickListener { finish() }
    }


    private fun submitMeme(userId: String) {
        val loading = showLoadingUi(this)
        val postData = Post.Data(
            topText = et_top_text_edit.text.toString(),
            bottomText = et_bottom_text_edit.text.toString(),
            url = et_img_url_edit.text.toString(),
            idUser = userId
        )
        loading.show()
        volley.add(
            PostRepository().addPost(
                post = postData,
                onSuccess = {
                    loading.dismiss()
                    val result = Gson().fromJson(it, Post::class.java)
                    if (result != null) {
                        showSortToast(applicationContext, "Meme uploaded")
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