package mx.aplazo.themeal.domain

import mx.aplazo.themeal.data.MealRepository
import javax.inject.Inject

class MealsByCategoryUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(category: String) =
        repository.getMealsByCategory(category = category)
}