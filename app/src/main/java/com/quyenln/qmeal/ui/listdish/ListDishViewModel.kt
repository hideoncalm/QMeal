package com.quyenln.qmeal.ui.listdish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.repository.IMealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDishViewModel @Inject constructor(
    private val mealRepo: IMealRepository
) : ViewModel() {

    private val _meals = MutableLiveData<MutableList<Meal>>()
    val meals: LiveData<MutableList<Meal>>
        get() = _meals

    fun getMealsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mealRepo.getMealsByCategory(category)
            _meals.postValue(response.meals?.toMutableList())
        }
    }
}
