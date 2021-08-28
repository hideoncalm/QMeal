package com.quyenln.qmeal.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.ui.listdish.adapter.SearchAdapter
import com.quyenln.qmeal.util.CustomProgressBar
import com.quyenln.qmeal.util.closeKeyBoard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchAdapter.OnItemClickListener {

    private val viewModel: SearchViewModel by viewModels()
    private val adapter by lazy {
        SearchAdapter(this)
    }

    @Inject
    lateinit var loadingProgressBar: CustomProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDish.adapter = adapter
        editTextSearch.setOnEditorActionListener { _, _, _ ->
            val key = editTextSearch.text.toString()
            if (key.isEmpty()) {
                Snackbar.make(requireView(), "Fill text", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.searchMeals(key)
                buttonSearch.visibility = View.GONE
                buttonClearSearch.visibility = View.VISIBLE
                editTextSearch.closeKeyBoard(requireContext())
            }
            true
        }

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) loadingProgressBar.showProgressBar(requireActivity())
            else loadingProgressBar.dismissProgressBar()
        })
        viewModel.meals.observe(viewLifecycleOwner, {
            adapter.updateData(it)
        })

        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        buttonSearch.setOnClickListener {
            val key = editTextSearch.text.toString()
            if (key.isEmpty()) {
                Snackbar.make(requireView(), "Fill text", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.searchMeals(key)
                buttonSearch.visibility = View.GONE
                buttonClearSearch.visibility = View.VISIBLE
                editTextSearch.closeKeyBoard(requireContext())
            }
        }

        buttonClearSearch.setOnClickListener {
            editTextSearch.text = null
            buttonSearch.visibility = View.VISIBLE
            buttonClearSearch.visibility = View.GONE
        }
    }

    override fun onItemClick(meal: MealDetail) {
        val action = SearchFragmentDirections.actionSearchFragmentToDishDetailFragment(meal.id)
        findNavController().navigate(action)
    }

}
