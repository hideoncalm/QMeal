package com.quyenln.qmeal.ui.ingredient

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.ui.ingredient.adapter.IngredientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ingredient.*

@AndroidEntryPoint
class IngredientFragment : Fragment(R.layout.fragment_ingredient),
    IngredientAdapter.OnItemClickListener {

    private val adapter: IngredientAdapter by lazy { IngredientAdapter(this) }
    private val viewModel: IngredientViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerIngredient.adapter = adapter
        viewModel.apply {
            getIngredients()
            ingredients.observe(viewLifecycleOwner,
                {
                    adapter.updateData(it)
                })
        }
    }

    override fun onItemClick(ingredient: Ingredient) {
        val action =
            IngredientFragmentDirections.actionIngredientFragmentToIngredientDetailFragment(
                ingredient
            )
        findNavController().navigate(action)
    }
}
