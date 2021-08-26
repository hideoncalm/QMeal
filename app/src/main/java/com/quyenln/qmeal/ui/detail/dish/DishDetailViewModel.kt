package com.quyenln.qmeal.ui.detail.dish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.repository.IMealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DishDetailViewModel @Inject constructor(
    private val mealRepository: IMealRepository
) : ViewModel() {

    private val _mealDetail = MutableLiveData<MealDetail>()
    val mealDetail: LiveData<MealDetail>
        get() = _mealDetail
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun getMealById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mealRepository.getMealById(id)
            val meal = response.meals[0]
            meal.apply {
                youtubeLink = youtubeLink.substring(
                    youtubeLink.indexOf('=') + 1,
                    youtubeLink.lastIndex + 1
                )
            }
            _mealDetail.postValue(meal)
        }
    }

    fun insertMeal(meal: MealDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite.value == true) {
                mealRepository.deleteMeal(meal)
                _isFavorite.postValue(false)
            } else {
                mealRepository.insertMeal(meal)
                _isFavorite.postValue(true)
            }
        }
    }

    fun isMealFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mealRepository.isFavorite(id)
            Timber.d(result.toString())
            _isFavorite.postValue(result)
        }
    }
}
