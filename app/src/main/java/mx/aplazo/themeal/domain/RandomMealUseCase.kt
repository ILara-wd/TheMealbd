package mx.aplazo.themeal.domain

import mx.aplazo.themeal.data.MealRepository
import javax.inject.Inject

class RandomMealUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke() = repository.getRandomMeal()
}