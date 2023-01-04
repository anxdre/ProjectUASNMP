package com.ubaya.projectuasnmp.data.model
import com.google.gson.annotations.SerializedName



abstract class Post(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("bottomText")
    val bottomText: String? = null,
    @SerializedName("idPost")
    val idPost: String? = null,
    @SerializedName("idUser")
    val idUser: String? = null,
    @SerializedName("isHidden")
    val isHidden: String? = null,
    @SerializedName("liked")
    val liked: String? = null,
    @SerializedName("topText")
    val topText: String? = null,
    @SerializedName("totalLike")
    val totalLike: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("username")
    val username: String? = null
)