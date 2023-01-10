package com.ubaya.projectuasnmp.data.model

import com.google.gson.annotations.SerializedName


data class Leaderboard(
    @SerializedName("data")
    val `data`: MutableList<Data>? = null,
    @SerializedName("msg")
    val msg: String? = null
) {
    data class Data(
        @SerializedName("totalLike")
        val totalLike: String? = null,
        @SerializedName("username")
        val username: String? = null,
        @SerializedName("isHidden")
        val isHidden: String? = null
    )
}