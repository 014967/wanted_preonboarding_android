package com.example.wantedpreonboarding.presentation.topnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboarding.base.BaseFragment
import com.example.wantedpreonboarding.databinding.FragmentTopNewsBinding
import com.example.wantedpreonboarding.presentation.model.TopNews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopNewsFragment :
    BaseFragment<FragmentTopNewsBinding>(FragmentTopNewsBinding::inflate),
    (TopNews) -> Unit {

    private val topNewsAdapter: TopNewsAdapter by lazy {
        TopNewsAdapter(this)
    }

    private val topNewsViewModel: TopNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeTopNews()
    }

    private fun initUI() = with(binding) {
        rvTopNews.adapter = topNewsAdapter
        rvTopNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        topNewsViewModel.getTopNews()
    }
    private fun observeTopNews() = with(viewLifecycleOwner.lifecycleScope) {
        launch {
            repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
                topNewsViewModel.topNews.collect { state ->
                    when (state) {
                        is TopNewsUiStates.Loading -> {
                        }
                        is TopNewsUiStates.Success -> {
                            topNewsAdapter.submitList(state.news)
                        }
                    }
                }
            }
        }
    }

    override fun invoke(topNews: TopNews) {
        val action = TopNewsFragmentDirections.actionNavigationTopNewsToNewsDetailFragment(
            topNews
        )
        findNavController().navigate(action)
    }
}
