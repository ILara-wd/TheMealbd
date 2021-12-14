package mx.aplazo.themeal.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.R
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.databinding.FragmentCategoryBinding
import mx.aplazo.themeal.databinding.FragmentHomeBinding
import mx.aplazo.themeal.ui.home.adapter.CategoryAdapter
import mx.aplazo.themeal.ui.home.adapter.ListRecommendationAdapter
import mx.aplazo.themeal.ui.home.adapter.OnCategorySelectListener
import mx.aplazo.themeal.ui.home.adapter.OnSelectListener

@AndroidEntryPoint
class HomeFragment : Fragment(), OnSelectListener, OnCategorySelectListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var listRecommendationAdapter: ListRecommendationAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.getCategories()
        homeViewModel.getRandomMeal()
        observers()
        return binding.root
    }

    private fun observers() {
        homeViewModel.isLoading.observe(this, {
            binding.lottieAnimationView.isVisible = it
        })
        homeViewModel.randomMeal.observe(this, { mealRandom ->
            showMealRandom(mealRandom)
        })
        homeViewModel.categories.observe(this, { categories ->
            showCategories(categories)
        })
    }

    private fun showMealRandom(mealRandom: MutableList<MealDetail>) {
        binding.rvRecommendation.setHasFixedSize(true)
        listRecommendationAdapter = ListRecommendationAdapter(this)
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvRecommendation.adapter = listRecommendationAdapter
        listRecommendationAdapter.setData(mealRandom)
    }

    private fun showCategories(mealRandom: MutableList<Category>) {
        binding.rvCategory.setHasFixedSize(true)
        categoryAdapter = CategoryAdapter(requireActivity(), this)
        binding.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.setData(mealRandom)
    }

    override fun onClickMeal(mealDetail: MealDetail) {
        findNavController().navigate(R.id.action_homeFragment_to_searchMealFragment)
    }

    override fun onClickCategory(category: Category) {
        val bundle = bundleOf("category" to category)
        findNavController().navigate(R.id.action_homeFragment_to_categoryFragment, bundle)
    }


}