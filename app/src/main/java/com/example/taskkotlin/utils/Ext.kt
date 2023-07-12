package com.example.taskkotlin.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri:String){
    Glide.with(this).load(uri).into(this)
}
fun toast(context: Context,text:String){
    Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
}