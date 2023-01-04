package com.ubaya.projectuasnmp.data.model
import com.google.gson.annotations.SerializedName


data class Comment(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("created_At")
    val createdAt: String? = null,
    @SerializedName("firstname")
    val firstname: String? = null,
    @SerializedName("idUser")
    val idUser: String? = null,
    @SerializedName("isHidden")
    val isHidden: String? = null,
    @SerializedName("lastname")
    val lastname: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("username")
    val username: String? = null
)