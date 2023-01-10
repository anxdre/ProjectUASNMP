package com.ubaya.projectuasnmp.data.api.repositories

import User
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.ubaya.projectuasnmp.data.api.ApiFactory


class AuthRepository {
    fun register(
        user: User.Data, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.postRequest(
            url = "https://anxdre.my.id/Memeku/api/auth/register.php",
            onSuccess = {
                onSuccess(it)
            },
            onFailed = {
                onFailed(it)
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

    }

    fun editProfile(
        user: User.Data, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.postRequest(
            url = "https://anxdre.my.id/Memeku/api/auth/edit.php",
            onSuccess = {
                onSuccess(it)
            },
            onFailed = {
                onFailed(it)
            },
            multipart = HashMap(
                mutableMapOf(
                    Pair("idUser", "${user.idUser}"),
                    Pair("firstname", "${user.firstname}"),
                    Pair("lastname", "${user.lastname}"),
                    Pair("avatar", "${user.avatar}"),
                    Pair("isHidden", "${user.isHidden}"),
                )
            )
        )

    }


    fun login(
        username: String, password: String, onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.postRequest(
            url = "https://anxdre.my.id/Memeku/api/auth/login.php",
            onSuccess = {
                onSuccess(it)
            },
            onFailed = {
                onFailed(it)
            },
            multipart = HashMap(
                mutableMapOf(
                    Pair("username", username),
                    Pair("password", password),
                )
            )
        )
    }
}