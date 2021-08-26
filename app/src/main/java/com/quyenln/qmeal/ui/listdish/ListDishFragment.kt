package com.quyenln.qmeal.ui.listdish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.ui.listdish.adapter.MealAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dish_by_category.*

@AndroidEntryPoint
class ListDishFragment : Fragment(R.layout.fragment_dish_by_category),
    MealAdapter.OnItemClickListener {

    private val viewModel: ListDishViewModel by viewModels()
    private val adapter by lazy { MealAdapter(this) }
    private val arg: ListDishFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDish.adapter = adapter
        textToolbarTitle.text = arg.categoryName
        viewModel.getMealsByCategory(arg.categoryName)
        viewModel.meals.observe(viewLifecycleOwner, {
            adapter.updateData(it)
        })

        buttonSearch.setOnClickListener {
            findNavController().navigate(R.id.action_listDishFragment_to_searchFragment)
        }
        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onItemClick(meal: Meal) {
        val action = ListDishFragmentDirections.actionListDishFragmentToDishDetailFragment(meal.id)
        findNavController().navigate(action)
    }
}
