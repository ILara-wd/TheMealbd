package mx.aplazo.themeal.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.data.model.MealDetailsModel
import mx.aplazo.themeal.data.model.MealModel
import javax.inject.Inject

class MealService @Inject constructor(private val api: MealApiClient) {

    suspend fun getAllCategories(): MutableList<Category> =
        withContext(Dispatchers.IO) {
            val response = api.getAllCategories()
            response.body()?.categories ?: mutableListOf()
        }

    suspend fun getMealsByCategory(category: String): MutableList<MealModel> =
        withContext(Dispatchers.IO) {
            val response = api.getMealsByCategory(c = category)
            response.body() ?: mutableListOf()
        }

    suspend fun getDetailMeal(idMeal: String): MealDetail =
        withContext(Dispatchers.IO) {
            val response = api.getDetailMeal(i = idMeal)
            response.body()?.meals.orEmpty().first()
        }

    suspend fun getMealSearch(food: String): MutableList<MealDetail> =
        withContext(Dispatchers.IO) {
            val response = api.getMealSearch(f = food)
            response.body()?.meals ?: mutableListOf()
        }

    suspend fun getRandomMeal(): MutableList<MealDetail> =
        withContext(Dispatchers.IO) {
            val response = api.getRandomMeal()
            response.body()?.meals ?: mutableListOf()
        }

}