package com.example.registration.utils

import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {
    private const val NAME="registration"
    private const val MODE=Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(ctx:Context){
        preferences=ctx.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)->Unit){
        val editor=edit()
        operation(editor)
        editor.apply()
    }

    var user:String
    get() = preferences.getString("user","[]")!!.toString()
    set(value) {
       preferences.edit().putString("user",value).commit()
    }

    var switch:Boolean
    get() = preferences.getBoolean("switch",false)
        set(value) {
            preferences.edit().putBoolean("switch",value).commit()
        }

    var contact:String
    get() = preferences.getString("contact","[]")!!.toString()
    set(value) {
        preferences.edit().putString("contact",value).commit()
    }


}