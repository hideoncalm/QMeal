package com.quyenln.qmeal.ui.detail.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.model.MealResponse
import com.quyenln.qmeal.data.repository.IIngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientDetailViewModel @Inject constructor(
    private val ingredientRepo: IIngredientRepository
) : ViewModel() {

    private val _meals = MutableLiveData<MutableList<Meal>>()
    val meals: LiveData<MutableList<Meal>>
        get() = _meals
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun getMealsByIngredient(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: MealResponse? = ingredientRepo.getMealsByIngredient(id)
            response?.let { _meals.postValue(it.meals?.toMutableList()) }
        }
    }

    fun isFavoriteIngredient(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = ingredientRepo.isFavoriteIngredient(id)
            _isFavorite.postValue(result)
        }
    }

    fun insertIngredient(ingredient: Ingredient) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite.value == true) {
                ingredientRepo.deleteIngredient(ingredient)
                _isFavorite.postValue(false)
            } else {
                ingredientRepo.insertIngredient(ingredient)
                _isFavorite.postValue(true)
            }
        }
    }
}
