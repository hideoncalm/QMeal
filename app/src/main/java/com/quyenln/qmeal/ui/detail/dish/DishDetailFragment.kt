package com.quyenln.qmeal.ui.detail.dish

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseFragment
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.databinding.FragmentDishDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishDetailFragment : BaseFragment<FragmentDishDetailBinding, DishDetailViewModel>() {

    private var meal: MealDetail? = null
    private val args: DishDetailFragmentArgs by navArgs()
    override val viewModel: DishDetailViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_dish_detail


    override fun initViews() {
        binding.apply {
            viewModelDish = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycle.addObserver(binding.viewYoutube)
        viewModel.apply {
            getMealById(args.mealId)
            isMealFavorite(args.mealId)
            mealDetail.observe(viewLifecycleOwner, {
                meal = it
                initYoutube(it.youtubeLink)
                binding.apply {
                    textToolbarTitle.text = it.name
                    textNameDish.text = it.name
                    textDishDescription.text = it.title
                }
            })
            message.observe(viewLifecycleOwner, {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            })
        }
        binding.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }

            buttonFavorite.setOnClickListener {
                meal?.let { meal -> viewModel.insertMeal(meal) }
            }
        }
    }

    private fun initYoutube(id: String) {
        binding.viewYoutube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0F)
            }
        })
    }

}
