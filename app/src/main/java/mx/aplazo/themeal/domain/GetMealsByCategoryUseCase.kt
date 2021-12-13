package mx.aplazo.themeal.domain

import mx.aplazo.themeal.data.MealRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(category: String) =
        repository.getMealsByCategory(category = category)
}