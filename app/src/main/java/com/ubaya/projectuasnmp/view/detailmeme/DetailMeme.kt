package com.ubaya.projectuasnmp.view.detailmeme

import Post
import User
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.SharedPrefHelper
import com.ubaya.projectuasnmp.data.api.repositories.CommentRepository
import com.ubaya.projectuasnmp.data.model.Comment
import com.ubaya.projectuasnmp.util.fetchImageFromNetwork
import com.ubaya.projectuasnmp.util.showLoadingUi
import com.ubaya.projectuasnmp.util.showSortToast
import kotlinx.android.synthetic.main.activity_detail_meme.*
import kotlinx.android.synthetic.main.layout_post_card.view.*

class DetailMeme : AppCompatActivity() {
    private lateinit var postData: Post.Data
    private lateinit var rvCommentAdapter: CommentAdapter
    lateinit var userData: User.Data
    val volley by lazy { Volley.newRequestQueue(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_meme)
        rvCommentAdapter = CommentAdapter()
        rv_comment_detail.adapter = rvCommentAdapter

        try {
            userData = SharedPrefHelper(applicationContext).getUser()
            postData = intent.getSerializableExtra("postData") as Post.Data
            fetchImageFromNetwork(postData.url.toString(), ll_img_detail.iv_meme_card)
            ll_img_detail.tv_like_card.text = "${postData.totalLike} likes"
            ll_img_detail.tv_comment_card.text = "${postData.totalComment} Comment"
            ll_img_detail.tv_date_card.text = postData.createdAt
            ll_img_detail.tv_title_top_card.text = postData.topText
            ll_img_detail.tv_title_bottom_card.text = postData.bottomText

            fetchCommentFromNetwork(postData.idPost.toString())
        } catch (ex: Exception) {
            showSortToast(this, "Error Getting Data")
        }

        iv_send_detail.setOnClickListener {
            if (!et_comment_form_detail.text.isNullOrEmpty()) {
                addCommentToPost()
            }
        }
    }

    private fun fetchCommentFromNetwork(postId: String) {
        val loadingUi = showLoadingUi(this)
        loadingUi.show()
        volley.add(
            CommentRepository().getCommentByPost(idPost = postId,
                onSuccess = {
                    rvCommentAdapter.setNewDataSet(Gson().fromJson(it, Comment::class.java).data)
                    loadingUi.dismiss()
                },
                onFailed = {
                    showSortToast(this, "Error contacting server")
                    loadingUi.dismiss()
                })
        )
    }

    private fun addCommentToPost() {
        volley.add(
            CommentRepository().addCommentToPost(
                et_comment_form_detail.text.toString(),
                postData.idPost.toString(),
                userData.idUser.toString(),
                onSuccess = {
                    fetchCommentFromNetwork(postData.idPost.toString())
                    et_comment_form_detail.text!!.clear()
                },
                onFailed = {
                    showSortToast(this, "Error contacting server")
                }
            )
        )
    }
}