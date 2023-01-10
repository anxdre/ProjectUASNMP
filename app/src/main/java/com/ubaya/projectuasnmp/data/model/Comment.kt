package com.ubaya.projectuasnmp.data.model

import com.google.gson.annotations.SerializedName


data class Comment(
    @SerializedName("data")
    val `data`: MutableList<Data>,
    @SerializedName("msg")
    val msg: String? = null
){
    data class Data(
        @SerializedName("comment")
        val comment: String? = null,
        @SerializedName("created_at")
        val createdAt: String? = null,
        @SerializedName("username")
        val username: String? = null
    )
}