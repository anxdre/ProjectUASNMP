package com.ubaya.projectuasnmp.data.api.repositories

import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.ubaya.projectuasnmp.data.api.ApiFactory

class CommentRepository() {

    fun getCommentByPost(
        idPost: String, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.getRequest(
            url = "https://anxdre.my.id/Memeku/api/comments/comments.php?postId=$idPost",
            onSuccess = {
                onSuccess(it)
            }, onFailed = {
                onFailed(it)
            })
    }


    //add comment
    fun addCommentToPost(
        comment: String, idPost: String, idUser: String, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.postRequest(
            url = "https://anxdre.my.id/Memeku/api/comments/comments.php",
            onSuccess = {
                onSuccess(it)
            },
            onFailed = {
                onFailed(it)
            },
            multipart = HashMap(
                mutableMapOf(
                    Pair("comment", comment),
                    Pair("userId", idUser),
                    Pair("postId", idPost),
                )
            )
        )
    }
}