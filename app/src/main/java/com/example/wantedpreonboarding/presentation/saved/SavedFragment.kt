package com.example.wantedpreonboarding.presentation.saved

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboarding.base.BaseFragment
import com.example.wantedpreonboarding.databinding.FragmentSavedBinding
import com.example.wantedpreonboarding.presentation.adapter.TopNewsAdapter
import com.example.wantedpreonboarding.presentation.model.TopNews
import com.example.wantedpreonboarding.presentation.viewmodel.SavedNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment :
    BaseFragment<FragmentSavedBinding>(FragmentSavedBinding::inflate),
    (TopNews) -> Unit {

    private val savedViewModel: SavedNewsViewModel by viewModels()

    private val topNewsAdapter: TopNewsAdapter by lazy {
        TopNewsAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSavedTopNews()
        initUI()
    }

    private fun initUI() = with(binding) {
        rvTopNews.adapter = topNewsAdapter
        rvTopNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        savedViewModel.getSavedTopNewsList()
    }

    private fun observeSavedTopNews() = with(viewLifecycleOwner.lifecycleScope) {
        launch {
            repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
                savedViewModel.savedTopNewsList.collect { state ->
                    topNewsAdapter.submitList(state)
                }
            }
        }
    }

    override fun invoke(topNews: TopNews) {
        val action = SavedFragmentDirections.actionNavigationSavedToNewsDetailFragment(
            topNews,
            topNews.title
        )
        findNavController().navigate(action)
    }
}
