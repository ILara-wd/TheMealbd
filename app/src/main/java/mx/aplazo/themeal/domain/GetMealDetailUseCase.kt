package mx.aplazo.themeal.domain

import mx.aplazo.themeal.data.MealRepository
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(idMeal: String) =
        repository.getDetailMeal(idMeal = idMeal)
}