package com.quyenln.qmeal.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { postValue(false) }
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    fun showLoading() {
        _isLoading.postValue(true)
    }

    fun hideLoading() {
        _isLoading.postValue(false)
    }

    fun setMessage(s : String){
        _message.postValue(s)
    }
}
