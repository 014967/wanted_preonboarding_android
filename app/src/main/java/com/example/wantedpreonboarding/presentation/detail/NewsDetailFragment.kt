package com.example.wantedpreonboarding.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.wantedpreonboarding.R
import com.example.wantedpreonboarding.base.BaseFragment
import com.example.wantedpreonboarding.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(FragmentNewsDetailBinding::inflate) {
    private val args by navArgs<NewsDetailFragmentArgs>()
    private val newsDetailViewModel: NewsDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNewsSavedState()
        initUI()
    }

    private fun observeNewsSavedState() = with(viewLifecycleOwner.lifecycleScope) {
        launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                newsDetailViewModel.savedState.collect { state ->
                    when (state) {
                        true -> {
                            binding.ivStar.isSelected = true
                        }
                        false -> {
                            binding.ivStar.isSelected = false
                        }
                    }
                }
            }
        }
    }

    private fun initUI() = with(binding) {
        args.topNews.apply {
            newsDetailViewModel.checkSaved(args.topNews.title)
            tvNewsDescription.text = args.topNews.description
            tvNewsTitle.text = args.topNews.title
            tvNewsWriter.text = args.topNews.writer
            tvNewsWritedTime.text = getString(R.string.news_writed_time, args.topNews.writedTime)
            Glide.with(ivNewsImage).load(args.topNews.imageUrl)
                .transform(CenterCrop(), RoundedCorners(10)).into(ivNewsImage)
        }
        ivStar.setOnClickListener { star ->
            star.isSelected = !star.isSelected
            if (star.isSelected) {
                // Room
                newsDetailViewModel.savedNews(args.topNews)
            } else {
                if (newsDetailViewModel.savedState.value) {
                    newsDetailViewModel.deleteSavedNews(args.topNews.title)
                }
            }
        }
    }
}
