package com.ubaya.projectuasnmp.data

import User
import android.content.Context

class SharedPrefHelper(val context: Context) {
    private val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    fun storeUser(user: User.Data) {
        with(sharedPref.edit()) {
            putString("idUser", user.idUser)
            putString("username", user.username)
            putString("firstname", user.firstname)
            putString("lastname", user.lastname)
            putString("avatar", user.avatar)
            putString("isHidden", user.isHidden)
            putString("createdAt", user.createdAt)
        }.apply()
    }

    fun deleteUser() {
        sharedPref.edit().clear().apply()
    }

    fun getUser(): User.Data {
        return User.Data(
            sharedPref.getString("avatar",null),
            sharedPref.getString("createdAt",null),
            sharedPref.getString("firstname",null),
            sharedPref.getString("idUser",null),
            sharedPref.getString("isHidden",null),
            sharedPref.getString("lastname",null),
            sharedPref.getString("password",""),
            sharedPref.getString("username",null)
        )
    }
}