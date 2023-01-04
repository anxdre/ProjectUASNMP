package com.ubaya.projectuasnmp.data.api.repositories

import com.android.volley.VolleyError
import com.google.gson.Gson
import com.ubaya.projectuasnmp.data.api.ApiFactory
import com.ubaya.projectuasnmp.data.model.BaseBody
import com.ubaya.projectuasnmp.data.model.User


class AuthRepository(private val apiFactory: ApiFactory) {

    fun register(user: User): BaseBody<User>? {
        var result: BaseBody<User>? = null
        try {
            apiFactory.postRequest(
                url = "http://localhost/Memeku/api/posts/posts.php",
                onSuccess = {
//                    result = Gson().fromJson(it, User::class.java)
                },
                onFailed = {
                    throw VolleyError(it.message)
                },
                multipart = HashMap(
                    mutableMapOf(
                        Pair("username", "${user.username}"),
                        Pair("firstname", "${user.firstname}"),
                        Pair("lastname", "${user.lastname}"),
                        Pair("password", "${user.password}"),
                        Pair("avatar", "${user.avatar}"),
                        Pair("isHidden", "${user.isHidden}"),
                    )
                )
            )
        } catch (e: Exception) {
            throw e
        }
        return result
    }

    fun login(username: String, password: String): BaseBody<User>? {
        var result: BaseBody<User>? = null
        try {
            apiFactory.postRequest(
                url = "http://localhost/Memeku/api/posts/posts.php",
                onSuccess = {
//                    result = Gson().fromJson(it, User::class.java)
                },
                onFailed = {
                    throw VolleyError(it.message)
                },
                multipart = HashMap(
                    mutableMapOf(
                        Pair("username", username),
                        Pair("password", password),
                    )
                )
            )
        } catch (e: Exception) {
            throw e
        }
        return result
    }
}