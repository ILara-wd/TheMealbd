package mx.aplazo.themeal.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.aplazo.themeal.R
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.FragmentHomeBinding
import mx.aplazo.themeal.ui.home.adapter.CategoryAdapter
import mx.aplazo.themeal.ui.home.adapter.MealRecipeAdapter
import mx.aplazo.themeal.ui.home.adapter.OnCategorySelectListener
import mx.aplazo.themeal.ui.home.adapter.OnSelectListener
import okhttp3.Dispatcher

@AndroidEntryPoint
class HomeFragment : Fragment(), OnSelectListener, OnCategorySelectListener, View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var mealRecipeAdapter: MealRecipeAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.toolbarHome.ibSearch.setOnClickListener(this)
        binding.toolbarHome.clSearch.setOnClickListener(this)
        homeViewModel.getCategories()
        getRandomTime()
        observers()
        return binding.root
    }

    private fun getRandomTime() {
        CoroutineScope(IO).launch {
            delay(9000)
            CoroutineScope(Main).launch {
                homeViewModel.getRandomMeal()
                getRandomTime()
            }
        }
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
        mealRecipeAdapter = MealRecipeAdapter(this)
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvRecommendation.adapter = mealRecipeAdapter
        mealRecipeAdapter.setData(mealRandom)
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
        val mealFilter = MealFilter(
            strMeal = mealDetail.strMeal.orEmpty(),
            strMealThumb = mealDetail.strMealThumb.orEmpty(),
            idMeal = mealDetail.idMeal.orEmpty()
        )
        val bundle = bundleOf("mealFilter" to mealFilter)
        findNavController().navigate(R.id.action_homeFragment_to_searchMealFragment, bundle)
    }

    override fun onClickCategory(category: Category) {
        val bundle = bundleOf("category" to category)
        findNavController().navigate(R.id.action_homeFragment_to_categoryFragment, bundle)
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_homeFragment_to_SearchFragment)
    }

}