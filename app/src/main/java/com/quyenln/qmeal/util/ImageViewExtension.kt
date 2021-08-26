package com.quyenln.qmeal.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.quyenln.qmeal.R

fun ImageView.loadImageFromUrl(url : String){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.pizza)
        .into(this)
}
