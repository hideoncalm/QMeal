package com.quyenln.qmeal.ui.favorite.dish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.base.BaseViewModel
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.repository.IMealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteDishViewModel @Inject constructor(
    private val mealRepo: IMealRepository
) : BaseViewModel() {
    private val _meals = MutableLiveData<MutableList<MealDetail>>()
    val meals: LiveData<MutableList<MealDetail>>
        get() = _meals

    fun getMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            val m = mealRepo.getMeals()
            if (m != null) {
                _meals.postValue(m.toMutableList())
            } else {
                _meals.postValue(mutableListOf())
            }
        }
    }

    fun deleteMeal(meal : MealDetail){
        viewModelScope.launch(Dispatchers.IO) {
            mealRepo.deleteMeal(meal)
        }
    }
}
