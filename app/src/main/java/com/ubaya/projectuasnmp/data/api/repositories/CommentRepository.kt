package com.ubaya.projectuasnmp.data.api.repositories

import com.android.volley.VolleyError
import com.ubaya.projectuasnmp.data.api.ApiFactory
import com.ubaya.projectuasnmp.data.model.BaseBody
import com.ubaya.projectuasnmp.data.model.Comment

class CommentRepository(private val apiFactory: ApiFactory) {

    fun getCommentByPost(idPost: Int): Comment? {
        var result: Comment? = null
        try {
            apiFactory.getRequest(
                url = "http://localhost/Memeku/api/comments/comments.php?postId=$idPost",
                onSuccess = {
//                    result = Json.decodeFromString<Comment>(it)
                }, onFailed = {
                    throw VolleyError(it.message)
                })
        } catch (e: Exception) {
            throw e
        }
        return result
    }


    //add comment
    fun addCommentToPost(comment: String, idPost: Int, idUser: Int): BaseBody<Comment>? {
        var result: BaseBody<Comment>? = null
        try {
            apiFactory.postRequest(
                url = "http://localhost/Memeku/api/comments/comments.php",
                onSuccess = {
//                    result = Json.decodeFromString<BaseBody<Comment>>(it)
                },
                onFailed = {
                    throw VolleyError(it.message)
                },
                multipart = HashMap(
                    mutableMapOf(
                        Pair("comment", comment),
                        Pair("userId", idUser.toString()),
                        Pair("postId", idPost.toString()),
                    )
                )
            )
        } catch (e: Exception) {
            throw e
        }
        return result
    }
}