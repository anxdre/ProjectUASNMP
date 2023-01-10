package com.ubaya.projectuasnmp.data.api.repositories

import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.ubaya.projectuasnmp.data.api.ApiFactory

class LeaderboardRepository {
    fun getLeaderboard(
        onSuccess: (String) -> Unit,
        onFailed: (VolleyError) -> Unit
    ): StringRequest {
        return ApiFactory.getRequest(
            url = "https://anxdre.my.id/Memeku/api/leaderboard/like.php",
            onSuccess = {
                onSuccess(it)
            }, onFailed = {
                onFailed(it)
            })
    }
}