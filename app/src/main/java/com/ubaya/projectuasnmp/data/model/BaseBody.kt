package com.ubaya.projectuasnmp.data.model

import com.google.gson.annotations.SerializedName

class BaseBody<T>(
    @SerializedName("msg")
    var msg: String? = null,
    @SerializedName("data")
    var data: T? = null
)