package mx.aplazo.themeal.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.domain.MealsByCategoryUseCase
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val mealsByCategoryUseCase: MealsByCategoryUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val recipeByCategory = MutableLiveData<MutableList<MealFilter>>()

    fun getRecipeByCategory(category: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val mealByCategory = mealsByCategoryUseCase(category = category)
            recipeByCategory.postValue(mealByCategory)
            isLoading.postValue(false)
        }
    }

}