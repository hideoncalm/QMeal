package com.quyenln.qmeal.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Category
import com.quyenln.qmeal.ui.category.adapter.CategoryAdapter
import com.quyenln.qmeal.util.CustomProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category), CategoryAdapter.OnItemClickListener {

    private val adapter by lazy {
        CategoryAdapter(this)
    }

    @Inject
    lateinit var loadingProgressBar: CustomProgressBar

    private val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCategory.adapter = adapter
        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) loadingProgressBar.showProgressBar(requireActivity())
            else loadingProgressBar.dismissProgressBar()
        })
        viewModel.categories.observe(viewLifecycleOwner,
            {
                adapter.updateData(it)
            })
        buttonSearch.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_searchFragment2)
        }
    }

    override fun onItemClick(category: Category) {
        val action =
            CategoryFragmentDirections.actionCategoryFragmentToListDishFragment(category.name)
        findNavController().navigate(action)
    }
}
