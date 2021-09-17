package com.quyenln.qmeal.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding ?: throw IllegalStateException("Binding is not Valid")

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        initViews()
    }

    abstract fun initViews()
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
