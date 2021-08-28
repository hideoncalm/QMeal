package com.quyenln.qmeal.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.quyenln.qmeal.R

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.pizza)
        .into(this)
}

fun View.closeKeyBoard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}
