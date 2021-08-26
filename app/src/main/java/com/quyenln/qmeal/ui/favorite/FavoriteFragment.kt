package com.quyenln.qmeal.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.ui.favorite.adapter.FavoriteFragmentAdapter
import com.quyenln.qmeal.ui.favorite.dish.FavoriteDishFragment
import com.quyenln.qmeal.ui.favorite.ingredient.FavoriteIngredientFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val fragments by lazy {
        listOf(FavoriteDishFragment(), FavoriteIngredientFragment())
    }
    private val titles by lazy {
        listOf("Meal", "Ingredient")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FavoriteFragmentAdapter(
            childFragmentManager,
            fragments,
            titles
        )
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        buttonSearch.setOnClickListener {
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }

}
