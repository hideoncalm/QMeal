package com.quyenln.qmeal.ui.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.base.BaseViewModel
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.repository.IIngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val ingredientRepo: IIngredientRepository
) : BaseViewModel() {

    private val _ingredients = MutableLiveData<MutableList<Ingredient>>()
    val ingredients: LiveData<MutableList<Ingredient>>
        get() = _ingredients

    fun getIngredients() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_ingredients.value == null) showLoading()
            val response = ingredientRepo.getIngredients()
            _ingredients.postValue(response.ingredients.toMutableList())
            hideLoading()
        }
    }
}
