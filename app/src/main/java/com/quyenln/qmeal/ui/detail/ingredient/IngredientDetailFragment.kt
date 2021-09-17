package com.quyenln.qmeal.ui.detail.ingredient

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseFragment
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.databinding.FragmentIngredientDetailBinding
import com.quyenln.qmeal.ui.listdish.adapter.MealAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientDetailFragment :
    BaseFragment<FragmentIngredientDetailBinding, IngredientDetailViewModel>(),
    OnItemClickListener<Meal> {

    override val layoutId: Int = R.layout.fragment_ingredient_detail
    override val viewModel: IngredientDetailViewModel by viewModels()
    private val arg: IngredientDetailFragmentArgs by navArgs()
    private val adapter by lazy {
        MealAdapter(this)
    }

    override fun initViews() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModelIngredient = viewModel
            recyclerDish.adapter = adapter
            textTitle.text = arg.ingredient.name
            textIngredient.text = arg.ingredient.title ?: ""
        }
        viewModel.apply {
            getMealsByIngredient(arg.ingredient.name)
            isFavoriteIngredient(arg.ingredient.id)
            meals.observe(viewLifecycleOwner, {
                adapter.updateData(it)
            })
            message.observe(viewLifecycleOwner, {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            })
        }

        with(binding) {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonFavorite.setOnClickListener {
                viewModel.insertIngredient(arg.ingredient)
            }
        }
    }

    override fun onItemClick(item: Meal) {
        val action =
            IngredientDetailFragmentDirections.actionIngredientDetailFragmentToDishDetailFragment(
                item.id
            )
        findNavController().navigate(action)
    }
}
