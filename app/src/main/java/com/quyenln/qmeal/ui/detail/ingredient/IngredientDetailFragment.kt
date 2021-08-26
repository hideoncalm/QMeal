package com.quyenln.qmeal.ui.detail.ingredient

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.ui.listdish.adapter.MealAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ingredient_detail.*

@AndroidEntryPoint
class IngredientDetailFragment : Fragment(R.layout.fragment_ingredient_detail),
    MealAdapter.OnItemClickListener {

    private val viewModel: IngredientDetailViewModel by viewModels()
    private val arg: IngredientDetailFragmentArgs by navArgs()
    private val adapter by lazy {
        MealAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDish.adapter = adapter
        textTitle.text = arg.ingredient.name
        textIngredient.text = arg.ingredient.title
        viewModel.apply {
            getMealsByIngredient(arg.ingredient.name)
            isFavoriteIngredient(arg.ingredient.id)
            meals.observe(viewLifecycleOwner, {
                adapter.updateData(it)
            })
            isFavorite.observe(viewLifecycleOwner, {
                if (it == true) {
                    buttonFavorite.visibility = View.GONE
                    buttonUnFavorite.visibility = View.VISIBLE
                } else {
                    buttonFavorite.visibility = View.VISIBLE
                    buttonUnFavorite.visibility = View.GONE
                }
            })
        }

        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        buttonFavorite.setOnClickListener {
            viewModel.insertIngredient(arg.ingredient)
            Snackbar.make(requireView(), "Inserted Success", Snackbar.LENGTH_SHORT).show()
        }
        buttonUnFavorite.setOnClickListener {
            viewModel.insertIngredient(arg.ingredient)
            Snackbar.make(requireView(), "Deleted Success", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(meal: Meal) {
        val action =
            IngredientDetailFragmentDirections.actionIngredientDetailFragmentToDishDetailFragment(
                meal.id
            )
        findNavController().navigate(action)
    }
}
