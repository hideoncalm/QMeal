package com.quyenln.qmeal.ui.ingredient

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.ui.ingredient.adapter.IngredientAdapter
import com.quyenln.qmeal.util.CustomProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.fragment_ingredient.*
import javax.inject.Inject

@AndroidEntryPoint
class IngredientFragment : Fragment(R.layout.fragment_ingredient),
    IngredientAdapter.OnItemClickListener {

    private val adapter: IngredientAdapter by lazy { IngredientAdapter(this) }
    private val viewModel: IngredientViewModel by viewModels()

    @Inject
    lateinit var loadingProgressBar: CustomProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerIngredient.adapter = adapter
        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) loadingProgressBar.showProgressBar(requireActivity())
            else loadingProgressBar.dismissProgressBar()
        })
        viewModel.apply {
            getIngredients()
            ingredients.observe(viewLifecycleOwner,
                {
                    adapter.updateData(it)
                })
        }
        buttonSearch.setOnClickListener {
            val action = IngredientFragmentDirections.actionIngredientFragmentToSearchFragment()
            findNavController().navigate(action)
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
