package com.example.taskkotlin.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(SHARED_NAME,MODE_PRIVATE)

    fun setUserEdit(name:String) {
        pref.edit().putString(KEY_FF,name).apply()
    }

    fun getUserEdit():String?{
        return pref.getString(KEY_FF,KEY_EDIT)
    }

    fun saveImage(image:String){
        pref.edit().putString(KEY_FOR_SAVE_IMAGE,image).apply()
    }

    fun getImage(): String {
        return pref.getString(KEY_FOR_SAVE_IMAGE, KEY_FOR_GET_IMAGE).toString()
    }

    fun isUserSeen():Boolean{
        return pref.getBoolean(KEY_FOR_NAME,false)
    }

    fun userSeen(){
        pref.edit().putBoolean(KEY_FOR_NAME, true).apply()
    }

    companion object{
        const val SHARED_NAME = "task_app"
        const val KEY_FOR_NAME = "isSeen"

        const val KEY_EDIT = "edit"
        const val KEY_FF = "ff"

        const val KEY_FOR_SAVE_IMAGE = "image_save"
        const val KEY_FOR_GET_IMAGE = "image_save"

    }
}