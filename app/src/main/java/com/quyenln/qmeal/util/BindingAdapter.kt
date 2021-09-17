package com.quyenln.qmeal.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setVisible")
fun View.setVisible(isVisible : Boolean){
    this.visibility = if(isVisible) View.VISIBLE else View.GONE
}
