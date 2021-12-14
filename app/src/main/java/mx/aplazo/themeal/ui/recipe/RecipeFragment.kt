package mx.aplazo.themeal.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.FragmentCategoryBinding
import mx.aplazo.themeal.databinding.FragmentMealDetailBinding
import mx.aplazo.themeal.ui.home.adapter.OnCategorySelectListener
import mx.aplazo.themeal.ui.home.adapter.OnSelectListener

@AndroidEntryPoint
class RecipeFragment : Fragment(), OnSelectListener, OnCategorySelectListener {

    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var mealFilter: MealFilter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        initView()
        observers()

        return binding.root
    }

    private fun initView() {
        binding.toolbarDetail.setNavigationOnClickListener { activity?.onBackPressed() }
    }


    private fun observers() {
    }


    override fun onClickMeal(mealDetail: MealDetail) {
    }

    override fun onClickCategory(category: Category) {
    }

    override fun setArguments(args: Bundle?) {
        mealFilter = args?.getSerializable("mealFilter") as MealFilter
    }


}