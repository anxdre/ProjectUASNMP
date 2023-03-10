package com.ubaya.projectuasnmp.data.api.repositories

import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.ubaya.projectuasnmp.data.api.ApiFactory

class PostRepository {
    //get all user
    fun getAllPost(
        idUser: String, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.getRequest(
            url = "https://anxdre.my.id/Memeku/api/posts/posts.php?userId=$idUser",
            onSuccess = {
                onSuccess(it)
            }, onFailed = {
                onFailed(it)
            })
    }

    //get post by user
    fun getByUser(
        idUser: String, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.getRequest(
            url = "https://anxdre.my.id/Memeku/api/posts/posts.php?userId=$idUser&mode=0",
            onSuccess = {
                onSuccess(it)
            }, onFailed = {
                onFailed(it)
            })

    }

    //add like
    fun addLike(
        idPost: String, idUser: String, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.postRequest(
            url = "https://anxdre.my.id/Memeku/api/posts/posts.php",
            onSuccess = {
                onSuccess(it)
            },
            onFailed = {
                onFailed(it)
            },
            multipart = HashMap(
                mutableMapOf(
                    Pair("postId", idPost),
                    Pair("userId", "$idUser")
                )
            )
        )
    }

    fun addPost(
        post: Post.Data, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.postRequest(
            url = "https://anxdre.my.id/Memeku/api/posts/posts.php",
            onSuccess = {
                onSuccess(it)
            },
            onFailed = {
                onFailed(it)
            },
            multipart = HashMap(
                mutableMapOf(
                    Pair("topText", "${post.topText}"),
                    Pair("bottomText", "${post.bottomText}"),
                    Pair("url", "${post.url}"),
                    Pair("userId", "${post.idUser}"),
                )
            )
        )

    }
}