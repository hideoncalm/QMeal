package com.quyenln.qmeal.ui.favorite.dish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.OnItemButtonClickListener
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.ui.favorite.FavoriteFragmentDirections
import com.quyenln.qmeal.ui.favorite.dish.adapter.FavoriteMealAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_dish.*

@AndroidEntryPoint
class FavoriteDishFragment : Fragment(R.layout.fragment_favorite_dish),
    OnItemClickListener<MealDetail>, OnItemButtonClickListener<MealDetail> {

    private val viewModel: FavoriteDishViewModel by viewModels()
    private val adapter by lazy {
        FavoriteMealAdapter(this, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDish.adapter = adapter
        viewModel.getMeals()
        viewModel.meals.observe(viewLifecycleOwner, {
            adapter.updateData(it)
        })
    }

    override fun onItemClick(item: MealDetail) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDishDetailFragment(item.id)
        findNavController().navigate(action)
    }

    override fun onButtonHeartClick(item: MealDetail) {
        viewModel.deleteMeal(item)
        viewModel.getMeals()
    }
}
