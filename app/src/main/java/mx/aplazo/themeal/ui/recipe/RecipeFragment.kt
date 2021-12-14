package mx.aplazo.themeal.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.databinding.FragmentCategoryBinding
import mx.aplazo.themeal.databinding.FragmentMealDetailBinding
import mx.aplazo.themeal.ui.home.adapter.OnCategorySelectListener
import mx.aplazo.themeal.ui.home.adapter.OnSelectListener

@AndroidEntryPoint
class RecipeFragment : Fragment(), OnSelectListener, OnCategorySelectListener {

    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)

        observers()

        return binding.root
    }


    private fun observers() {
    }


    override fun onClickMeal(mealDetail: MealDetail) {
    }

    override fun onClickCategory(category: Category) {
    }


}