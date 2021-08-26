package com.quyenln.qmeal.ui.favorite.ingredient

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.ui.favorite.FavoriteFragmentDirections
import com.quyenln.qmeal.ui.listdish.adapter.FavoriteIngredientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_ingredient.*

@AndroidEntryPoint
class FavoriteIngredientFragment : Fragment(R.layout.fragment_favorite_ingredient),
    FavoriteIngredientAdapter.OnItemClickListener {

    private val viewModel: FavoriteIngredientViewModel by viewModels()
    private val adapter by lazy {
        FavoriteIngredientAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerIngredient.adapter = adapter
        viewModel.getLocalIngredients()
        viewModel.ingredient.observe(viewLifecycleOwner, {
            adapter.updateData(it)
        })
    }

    override fun onItemClick(ingredient: Ingredient) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToIngredientDetailFragment(ingredient)
        findNavController().navigate(action)
    }

    override fun onButtonClick(ingredient: Ingredient) {
        viewModel.deleteIngredient(ingredient)
        viewModel.getLocalIngredients()
    }
}
