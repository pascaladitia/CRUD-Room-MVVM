package com.pascal.roomapp.helper

import android.content.Context
import android.content.SharedPreferences

class SessionManager(var context: Context) {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "LOGIN"

    var ISLOGIN = "isLogin"
    var EMAIL = "email"
    var PASSWORD = "password"

    init {
        pref = context.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()
    }

    var login: Boolean?
        get() = pref?.getBoolean(ISLOGIN, false)
        set(login) {
            editor?.putBoolean(ISLOGIN, true)
            editor?.commit()
        }

    var email: String?
        get() = pref?.getString(EMAIL, "")
        set(email) {
            editor?.putString(EMAIL, email)
            editor?.commit()
        }

    var password: String?
        get() = pref?.getString(PASSWORD, "")
        set(password) {
            editor?.putString(PASSWORD, password)
            editor?.commit()
        }

    fun logOut() {
        editor?.clear()
        editor?.commit()
    }
}