package mx.aplazo.themeal.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.R
import mx.aplazo.themeal.Tools.showImage
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.FragmentCategoryBinding
import mx.aplazo.themeal.ui.category.adapter.OnSelectFilterListener
import mx.aplazo.themeal.ui.category.adapter.RecipeFoodAdapter
import mx.aplazo.themeal.ui.home.HomeViewModel
import mx.aplazo.themeal.ui.home.adapter.CategoryAdapter
import mx.aplazo.themeal.ui.home.adapter.OnCategorySelectListener
import mx.aplazo.themeal.ui.home.adapter.OnSelectListener

@AndroidEntryPoint
class CategoryFragment : Fragment(), OnSelectFilterListener {

    private lateinit var recipeFoodAdapter: RecipeFoodAdapter
    private val categoryViewModel: CategoryViewModel by viewModels()
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val idCategory = "13"
    private val strCategory = "Breakfast"
    private val strCategoryThumb = "https://www.themealdb.com/images/category/breakfast.png"
    private val strCategoryDescription =
        "Breakfast is the first meal of a day. The word in English refers to breaking the fasting period of the previous night. There is a strong likelihood for one or more \\\"typical\\\", or \\\"traditional\\\", breakfast menus to exist in most places, but their composition varies widely from place to place, and has varied over time, so that globally a very wide range of preparations and ingredients are now associated with breakfast."

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        categoryViewModel.getRecipeByCategory(strCategory)
        initView()
        observers()
        return binding.root
    }

    private fun initView() {
        binding.toolbarFilter.setNavigationOnClickListener { activity?.onBackPressed() }
        binding.tvTitle.text = requireActivity().getString(R.string.text_recipes, strCategory)
        binding.tvDescCategories.text = strCategoryDescription
        requireContext().showImage(strCategoryThumb, binding.imgCategoriesBg)
        requireContext().showImage(strCategoryThumb, binding.imgCategories)
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
        findNavController().navigate(R.id.action_categoryFragment_to_searchMealFragment)
    }

}