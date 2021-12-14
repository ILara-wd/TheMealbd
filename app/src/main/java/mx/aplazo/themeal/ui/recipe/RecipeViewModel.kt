package mx.aplazo.themeal.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.domain.MealDetailUseCase
import mx.aplazo.themeal.domain.MealsByCategoryUseCase
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    val mealDetailUseCase: MealDetailUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val recipeDetails = MutableLiveData<MealDetail>()

    fun getRecipeDetail(idMeal: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val mealDetail = mealDetailUseCase(idMeal = idMeal)
            recipeDetails.postValue(mealDetail)
            isLoading.postValue(false)
        }
    }

}