package com.quyenln.qmeal.ui.detail.dish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.MealDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dish_detail.*

@AndroidEntryPoint
class DishDetailFragment : Fragment(R.layout.fragment_dish_detail) {

    private var meal: MealDetail? = null
    private val args: DishDetailFragmentArgs by navArgs()
    private val viewModel: DishDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewYoutube)
        viewModel.getMealById(args.mealId)
        viewModel.isMealFavorite(args.mealId)
        viewModel.mealDetail.observe(viewLifecycleOwner, {
            meal = it
            initYoutube(it.youtubeLink)
            textToolbarTitle.text = it.name
            textNameDish.text = it.name
            textDishDescription.text = it.title
        })

        viewModel.isFavorite.observe(viewLifecycleOwner, {
            if (it == true) {
                buttonFavorite.visibility = View.GONE
                buttonUnFavorite.visibility = View.VISIBLE
            } else {
                buttonFavorite.visibility = View.VISIBLE
                buttonUnFavorite.visibility = View.GONE
            }
        })
        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        buttonFavorite.setOnClickListener {
            meal?.let { meal -> viewModel.insertMeal(meal) }
            Snackbar.make(requireView(), "Inserted Success", Snackbar.LENGTH_SHORT).show()
        }

        buttonUnFavorite.setOnClickListener {
            meal?.let { meal -> viewModel.insertMeal(meal) }
            Snackbar.make(requireView(), "Deleted Success", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initYoutube(id: String) {
        viewYoutube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0F)
            }
        })
    }
}
