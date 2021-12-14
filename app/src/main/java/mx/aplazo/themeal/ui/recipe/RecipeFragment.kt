package mx.aplazo.themeal.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.R
import mx.aplazo.themeal.utils.Tools.openSource
import mx.aplazo.themeal.utils.Tools.shareSource
import mx.aplazo.themeal.utils.Tools.showImage
import mx.aplazo.themeal.utils.Tools.ternary
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.FragmentMealDetailBinding

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var mealFilter: MealFilter
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        recipeViewModel.getRecipeDetail(mealFilter.idMeal)
        initView()
        observers()
        return binding.root
    }

    private fun initView() {
        binding.toolbarDetail.setNavigationOnClickListener { activity?.onBackPressed() }
        binding.tvTitle.text = mealFilter.strMeal
        requireContext().showImage(mealFilter.strMealThumb, binding.imgThumb)
    }

    private fun observers() {
        recipeViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.lottieAnimationView.isVisible = it
        })
        recipeViewModel.recipeDetails.observe(viewLifecycleOwner, { mealDetail ->
            showDataDetail(mealDetail)
        })
    }

    private fun showDataDetail(mealDetail: MealDetail?) {
        binding.tvInstructions.text = mealDetail?.strInstructions
        binding.tvSubTitle.text = requireActivity().getString(
            R.string.text_detail_area_category,
            mealDetail?.strCategory,
            mealDetail?.strArea
        )
        binding.tvSource.setOnClickListener {
            requireActivity().openSource(mealDetail?.strSource.orEmpty())
        }
        binding.tvYoutube.setOnClickListener {
            requireActivity().openSource(mealDetail?.strYoutube.orEmpty())
        }
        binding.tvShareRecipe.setOnClickListener {
            requireActivity().shareSource(mealDetail?.strSource.orEmpty())
        }

        //region Ingredient & Measure
        val ingredient = ""
            .plus(
                mealDetail?.strIngredient1.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient1
                )
            )
            .plus(
                mealDetail?.strIngredient2.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient2
                )
            )
            .plus(
                mealDetail?.strIngredient3.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient3
                )
            )
            .plus(
                mealDetail?.strIngredient4.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient4
                )
            )
            .plus(
                mealDetail?.strIngredient5.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient5
                )
            )
            .plus(
                mealDetail?.strIngredient6.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient6
                )
            )
            .plus(
                mealDetail?.strIngredient7.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient7
                )
            )
            .plus(
                mealDetail?.strIngredient8.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient8
                )
            )
            .plus(
                mealDetail?.strIngredient9.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient9
                )
            )
            .plus(
                mealDetail?.strIngredient10.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient10
                )
            )
            .plus(
                mealDetail?.strIngredient11.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient11
                )
            )
            .plus(
                mealDetail?.strIngredient12.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient12
                )
            )
            .plus(
                mealDetail?.strIngredient13.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient13
                )
            )
            .plus(
                mealDetail?.strIngredient14.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient14
                )
            )
            .plus(
                mealDetail?.strIngredient15.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient15
                )
            )
            .plus(
                mealDetail?.strIngredient16.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient16
                )
            )
            .plus(
                mealDetail?.strIngredient17.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient17
                )
            )
            .plus(
                mealDetail?.strIngredient18.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient18
                )
            )
            .plus(
                mealDetail?.strIngredient19.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient19
                )
            )
            .plus(
                mealDetail?.strIngredient20.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strIngredient20
                )
            )

        val measure = ""
            .plus(
                mealDetail?.strMeasure1.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure1
                )
            )
            .plus(
                mealDetail?.strMeasure2.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure2
                )
            )
            .plus(
                mealDetail?.strMeasure3.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure3
                )
            )
            .plus(
                mealDetail?.strMeasure4.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure4
                )
            )
            .plus(
                mealDetail?.strMeasure5.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure5
                )
            )
            .plus(
                mealDetail?.strMeasure6.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure6
                )
            )
            .plus(
                mealDetail?.strMeasure7.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure7
                )
            )
            .plus(
                mealDetail?.strMeasure8.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure8
                )
            )
            .plus(
                mealDetail?.strMeasure9.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure9
                )
            )
            .plus(
                mealDetail?.strMeasure10.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure10
                )
            )
            .plus(
                mealDetail?.strMeasure11.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure11
                )
            )
            .plus(
                mealDetail?.strMeasure12.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure12
                )
            )
            .plus(
                mealDetail?.strMeasure13.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure13
                )
            )
            .plus(
                mealDetail?.strMeasure14.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure14
                )
            )
            .plus(
                mealDetail?.strMeasure15.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure15
                )
            )
            .plus(
                mealDetail?.strMeasure16.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure16
                )
            )
            .plus(
                mealDetail?.strMeasure17.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure17
                )
            )
            .plus(
                mealDetail?.strMeasure18.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure18
                )
            )
            .plus(
                mealDetail?.strMeasure19.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure19
                )
            )
            .plus(
                mealDetail?.strMeasure20.isNullOrEmpty().ternary("") ?: "\n \u2022 ".plus(
                    mealDetail?.strMeasure20
                )
            )
//endregion

        binding.tvIngredients.text = ingredient
        binding.tvMeasure.text = measure
    }

    override fun setArguments(args: Bundle?) {
        mealFilter = args?.getSerializable("mealFilter") as MealFilter
    }


}