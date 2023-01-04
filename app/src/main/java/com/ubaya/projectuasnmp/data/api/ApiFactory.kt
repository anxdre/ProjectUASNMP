package com.ubaya.projectuasnmp.data.api

import com.android.volley.Request.Method
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest

object ApiFactory{
    fun getRequest(
        url: String,
        onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return StringRequest(
            Method.GET, url,
            { onSuccess(it) },
            { onFailed(it) })
    }

    fun postRequest(
        url: String,
        multipart: HashMap<String, String>,
        onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return object : StringRequest(
            Method.POST, url,
            { onSuccess(it) },
            { onFailed(it) }) {
            override fun getHeaders(): MutableMap<String, String> {
                return multipart
            }
        }
    }
}