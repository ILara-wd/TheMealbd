package mx.aplazo.themeal.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.R
import mx.aplazo.themeal.Tools.showImage
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.FragmentCategoryBinding
import mx.aplazo.themeal.ui.category.adapter.OnSelectFilterListener
import mx.aplazo.themeal.ui.category.adapter.RecipeFoodAdapter

@AndroidEntryPoint
class CategoryFragment : Fragment(), OnSelectFilterListener {

    private lateinit var recipeFoodAdapter: RecipeFoodAdapter
    private val categoryViewModel: CategoryViewModel by viewModels()
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var category: Category

    override fun setArguments(args: Bundle?) {
        category = args?.getSerializable("category") as Category
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        categoryViewModel.getRecipeByCategory(category.strCategory)
        initView()
        observers()
        return binding.root
    }

    private fun initView() {
        binding.toolbarFilter.setNavigationOnClickListener { activity?.onBackPressed() }
        binding.tvTitle.text =
            requireActivity().getString(R.string.text_recipes, category.strCategory)
        binding.tvDescCategories.text = category.strCategoryDescription
        requireContext().showImage(category.strCategoryThumb, binding.imgCategoriesBg)
        requireContext().showImage(category.strCategoryThumb, binding.imgCategories)
    }

    private fun showRecipe(mealRandom: MutableList<MealFilter>) {
        binding.rvFilter.setHasFixedSize(true)
        recipeFoodAdapter = RecipeFoodAdapter(this)
        binding.rvFilter.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        binding.rvFilter.adapter = recipeFoodAdapter
        recipeFoodAdapter.setData(mealRandom)
    }

    private fun observers() {
        categoryViewModel.recipeByCategory.observe(viewLifecycleOwner, { mealFilter ->
            showRecipe(mealFilter)
        })
        categoryViewModel.isLoading.observe(this, {
            binding.lottieAnimationView.isVisible = it
        })
    }

    override fun onClickMeal(mealFilter: MealFilter) {
        val bundle = bundleOf("mealFilter" to mealFilter)
        findNavController().navigate(R.id.action_categoryFragment_to_searchMealFragment, bundle)
    }

}