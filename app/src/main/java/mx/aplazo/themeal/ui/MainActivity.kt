package mx.aplazo.themeal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import mx.aplazo.themeal.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity()/*, OnSelectListener, OnCategorySelectListener */{

    private lateinit var binding: ActivityMainBinding
/*    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var listRecommendationAdapter: ListRecommendationAdapter
    private lateinit var categoryAdapter: CategoryAdapter*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*        categoryViewModel.getCategories()
        categoryViewModel.getRandomMeal()
        observers()*/
    }
/*

    private fun observers() {
        categoryViewModel.isLoading.observe(this, {
            binding.lottieAnimationView.isVisible = it
        })
        categoryViewModel.randomMeal.observe(this, { mealRandom ->
            showMealRandom(mealRandom)
        })
        categoryViewModel.categories.observe(this, { categories ->
            showCategories(categories)
        })
    }

    private fun showMealRandom(mealRandom: MutableList<MealDetail>) {
        binding.rvRecommendation.setHasFixedSize(true)
        listRecommendationAdapter = ListRecommendationAdapter(this)
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvRecommendation.adapter = listRecommendationAdapter
        listRecommendationAdapter.setData(mealRandom)
    }

    private fun showCategories(mealRandom: MutableList<Category>) {
        binding.rvCategory.setHasFixedSize(true)
        categoryAdapter = CategoryAdapter(this@MainActivity, this)
        binding.rvCategory.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.setData(mealRandom)
    }

    override fun onClickMeal(mealDetail: MealDetail) {
//        val context: Context = it!!.context
//        val intent = Intent(context, DetailActivity::class.java)
//        intent.putExtra(DetailActivity.EXTRA_RECIPE, data)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        context.startActivity(intent)
    }

    override fun onClickCategory(category: Category) {
//        val context: Context = it!!.context
//        val intent = Intent(context, FilterRecipesActivity::class.java)
//        intent.putExtra(FilterRecipesActivity.EXTRA_CATEGORY, data)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        context.startActivity(intent)
    }
*/

}