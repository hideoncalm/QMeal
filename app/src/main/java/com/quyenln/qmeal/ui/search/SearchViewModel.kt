package com.quyenln.qmeal.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.repository.IMealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mealRepo: IMealRepository
) : ViewModel() {

    private val _meals = MutableLiveData<MutableList<MealDetail>>()
    val meals: LiveData<MutableList<MealDetail>>
        get() = _meals
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun searchMeals(s: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val mealDetails = mutableListOf<MealDetail>()
            val response1 = mealRepo.searchMealByName(s)
            val response2 = mealRepo.searchMealsByFirstLetter(s[0])
            response2?.meals?.let {
                mealDetails.addAll(it)
            }
            response1?.meals?.let {
                mealDetails.addAll(it)
            }
            _meals.postValue(mealDetails)
            _isLoading.postValue(false)
        }
    }
}
