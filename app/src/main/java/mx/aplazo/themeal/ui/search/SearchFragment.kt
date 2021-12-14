package mx.aplazo.themeal.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.R
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.FragmentSearchBinding
import mx.aplazo.themeal.ui.home.adapter.MealRecipeAdapter
import mx.aplazo.themeal.ui.home.adapter.OnSelectListener

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener, OnSelectListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        initView()
        observers()
        return binding.root
    }

    private fun initView() {
        binding.includeToolbar.toolbarSearch.setNavigationOnClickListener { activity?.onBackPressed() }
        binding.includeToolbar.svRecipe.setOnQueryTextListener(this)
    }

    private fun observers() {
        searchViewModel.recipeList.observe(viewLifecycleOwner, {
            showMeals(it)
        })
        searchViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.lottieAnimationView.isVisible = it
        })
    }

    private lateinit var mealRecipeAdapter: MealRecipeAdapter

    private fun showMeals(recipeList: MutableList<MealDetail>) {
        binding.rvRecipe.setHasFixedSize(true)
        mealRecipeAdapter = MealRecipeAdapter(this)
        binding.rvRecipe.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvRecipe.adapter = mealRecipeAdapter
        mealRecipeAdapter.setData(recipeList)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchViewModel.searchFood(food = query.orEmpty())
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean = true

    override fun onClickMeal(mealDetail: MealDetail) {
        val mealFilter = MealFilter(
            strMeal = mealDetail.strMeal.orEmpty(),
            strMealThumb = mealDetail.strMealThumb.orEmpty(),
            idMeal = mealDetail.idMeal.orEmpty()
        )
        val bundle = bundleOf("mealFilter" to mealFilter)
        findNavController().navigate(R.id.action_SearchFragment_to_mealDetailFragment, bundle)
    }

}