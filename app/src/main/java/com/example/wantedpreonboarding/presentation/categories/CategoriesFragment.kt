package com.example.wantedpreonboarding.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.wantedpreonboarding.R
import com.example.wantedpreonboarding.base.BaseFragment
import com.example.wantedpreonboarding.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() = with(binding) {
        ivBusiness.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_business)
            )
            findNavController().navigate(action)
        }
        ivEntertainment.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_entertainment)
            )
            findNavController().navigate(action)
        }
        ivGeneral.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_general)
            )
            findNavController().navigate(action)
        }
        ivHealth.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_health)
            )
            findNavController().navigate(action)
        }
        ivScience.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_science)
            )
            findNavController().navigate(action)
        }
        ivSports.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_sports)
            )
            findNavController().navigate(action)
        }
        ivTechnology.setOnClickListener {
            val action = CategoriesFragmentDirections.actionNavigationCategoriesToNavigationCategoriesTopNews(
                getString(R.string.category_technology)
            )
            findNavController().navigate(action)
        }
    }
}
