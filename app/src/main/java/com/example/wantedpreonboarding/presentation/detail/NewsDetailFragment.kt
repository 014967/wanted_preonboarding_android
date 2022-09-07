package com.example.wantedpreonboarding.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.wantedpreonboarding.base.BaseFragment
import com.example.wantedpreonboarding.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(FragmentNewsDetailBinding::inflate) {
    private val args by navArgs<NewsDetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
