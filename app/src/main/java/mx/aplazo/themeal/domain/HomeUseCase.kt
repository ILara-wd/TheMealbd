package mx.aplazo.themeal.domain

import mx.aplazo.themeal.data.MealRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke() = repository.getAllCategories()
}