package com.jasenjo.sdos.prueba.persistence.sharedpreference

import android.app.Application
import android.content.Context

class SdosPreferenceManager(context: Context) {

    private val sharedPreferences = context.applicationContext.getSharedPreferences(Application::class.java.simpleName, Context.MODE_PRIVATE)

    fun getString(key: String): String? = sharedPreferences.getString(key, null)

    fun setString(key: String, value: String) {
        sharedPreferences.edit().run {
            putString(key, value)
            apply()
        }
    }

    fun getLong(key: String): Long = sharedPreferences.getLong(key, 0L)

    fun setLong(key: String, value: Long) {
        sharedPreferences.edit().run {
            putLong(key, value)
            apply()
        }
    }

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().run {
            putBoolean(key, value)
            apply()
        }
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().run {
            clear()
            commit()
        }
    }

}