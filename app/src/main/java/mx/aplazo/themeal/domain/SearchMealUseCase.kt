package mx.aplazo.themeal.domain

import mx.aplazo.themeal.data.MealRepository
import javax.inject.Inject

class SearchMealUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(food: String) = repository.getMealSearch(food = food)
}