package com.quyenln.qmeal.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.base.BaseViewModel
import com.quyenln.qmeal.data.model.Category
import com.quyenln.qmeal.data.repository.ICategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val cateRepo: ICategoryRepository
) : BaseViewModel() {

    private val _categories = MutableLiveData<MutableList<Category>>()
    val categories: LiveData<MutableList<Category>>
        get() = _categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_categories.value == null) showLoading()
            val response = cateRepo.getCategories()
            _categories.postValue(response.categories.toMutableList())
            hideLoading()
        }
    }
}
