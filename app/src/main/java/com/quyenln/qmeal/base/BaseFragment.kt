package com.quyenln.qmeal.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding ?: throw IllegalStateException("Binding is not valid")

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
            .apply { _binding = this }
            .root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initViews()
    }

    protected open fun observeViewModel() =
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer(::handleLoading))
        }

    private fun handleLoading(isLoading: Boolean?) {
        if (isLoading == true) showLoading() else hideLoading()
    }

    open fun hideLoading() {}

    open fun showLoading() {}

    abstract fun initViews()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
