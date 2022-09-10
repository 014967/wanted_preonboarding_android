package com.example.wantedpreonboarding.presentation.categories.categoriesnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboarding.base.BaseFragment
import com.example.wantedpreonboarding.databinding.FragmentCategoriesNewsBinding
import com.example.wantedpreonboarding.presentation.adapter.TopNewsAdapter
import com.example.wantedpreonboarding.presentation.model.TopNews
import com.example.wantedpreonboarding.presentation.topnews.TopNewsUiStates
import com.example.wantedpreonboarding.presentation.viewmodel.TopNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesNewsFragment :
    BaseFragment<FragmentCategoriesNewsBinding>(FragmentCategoriesNewsBinding::inflate),
    (TopNews) -> Unit {

    private val topNewsViewModel: TopNewsViewModel by activityViewModels()
    private val args: CategoriesNewsFragmentArgs by navArgs()

    private val topNewsAdapter: TopNewsAdapter by lazy {
        TopNewsAdapter(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTopNews()
        initUI()
    }

    private fun initUI() = with(binding) {
        rvTopNews.adapter = topNewsAdapter
        rvTopNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        args.category.let { category ->
            topNewsViewModel.getTopNews(category = category)
        }
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
        val action = CategoriesNewsFragmentDirections.actionNavigationCategoriesTopNewsToNavigationNewsDetail(
            topNews,
            topNews.title
        )
        findNavController().navigate(action)
    }
}
