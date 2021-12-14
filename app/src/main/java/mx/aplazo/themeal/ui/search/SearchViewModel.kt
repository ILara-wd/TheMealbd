package mx.aplazo.themeal.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.domain.SearchMealUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchMealUseCase: SearchMealUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val recipeList = MutableLiveData<MutableList<MealDetail>>()

    fun searchFood(food: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val mealDetail = searchMealUseCase(food = food)
            recipeList.postValue(mealDetail)
            isLoading.postValue(false)
        }
    }

}