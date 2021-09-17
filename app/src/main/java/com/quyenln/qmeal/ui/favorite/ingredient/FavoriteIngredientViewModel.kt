package com.quyenln.qmeal.ui.favorite.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyenln.qmeal.base.BaseViewModel
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.repository.IIngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteIngredientViewModel @Inject constructor(
    private val ingredientRepo: IIngredientRepository
) : BaseViewModel() {

    private val _ingredients = MutableLiveData<MutableList<Ingredient>>()
    val ingredient: LiveData<MutableList<Ingredient>>
        get() = _ingredients

    fun getLocalIngredients() {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredient = ingredientRepo.getLocalIngredients()
            if (ingredient != null) {
                _ingredients.postValue(ingredient.toMutableList())
            } else {
                _ingredients.postValue(mutableListOf())
            }
        }
    }

    fun deleteIngredient(ingredient: Ingredient) {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientRepo.deleteIngredient(ingredient)
        }
    }
}
