package br.com.watermager.watermanagerapp.Utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import br.com.watermager.watermanagerapp.Models.User

class UserShared(activity: Activity) {
    lateinit var sharedPrefs : SharedPreferences
    val PREF_NAME = "watermanager"

    init {
        sharedPrefs = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
    val SERIAL_KEY = "serial"
    val TOKEN_KEY = "token"

    public fun readUser(): User {
        val user = User()
        user.token = sharedPrefs.getString(TOKEN_KEY, "")
        user.serial = sharedPrefs.getString(SERIAL_KEY, "")
        return user
    }

    public fun addTokenAndSerial(token: String, serial: String) {
        val editor = sharedPrefs.edit()
        editor.putString(TOKEN_KEY, token)
        editor.putString(SERIAL_KEY, serial)
        editor.apply()
    }
}