package com.quyenln.qmeal.ui.favorite.dish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.ui.favorite.FavoriteFragmentDirections
import com.quyenln.qmeal.ui.listdish.adapter.FavoriteMealAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_dish.*

@AndroidEntryPoint
class FavoriteDishFragment : Fragment(R.layout.fragment_favorite_dish),
    FavoriteMealAdapter.OnItemClickListener {

    private val viewModel: FavoriteDishViewModel by viewModels()
    private val adapter by lazy {
        FavoriteMealAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDish.adapter = adapter
        viewModel.getMeals()
        viewModel.meals.observe(viewLifecycleOwner, {
            adapter.updateData(it)
        })
    }

    override fun onItemClick(meal: MealDetail) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDishDetailFragment(meal.id)
        findNavController().navigate(action)
    }

    override fun onButtonClick(meal: MealDetail) {
        viewModel.deleteMeal(meal)
        viewModel.getMeals()
    }
}
