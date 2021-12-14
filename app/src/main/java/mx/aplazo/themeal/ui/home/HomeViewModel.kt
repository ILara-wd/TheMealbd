package mx.aplazo.themeal.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.domain.HomeUseCase
import mx.aplazo.themeal.domain.RandomMealUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val homeUseCase: HomeUseCase,
    val randomMealUseCase: RandomMealUseCase
) : ViewModel() {

    val categories: MutableLiveData<MutableList<Category>> = MutableLiveData()
    val randomMeal: MutableLiveData<MutableList<MealDetail>> = MutableLiveData()
    val showMessage: MutableLiveData<String> = MutableLiveData("")
    val isLoading = MutableLiveData<Boolean>()

    fun getCategories() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = homeUseCase()
            if (response.isEmpty()) {
                showMessage.postValue("Categories Empty")
                isLoading.postValue(false)
            } else {
                categories.postValue(response)
                isLoading.postValue(false)
            }
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val mealRandom = randomMealUseCase()
            randomMeal.postValue(mealRandom)
            isLoading.postValue(false)
        }
    }

}