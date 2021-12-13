package mx.aplazo.themeal.data

import mx.aplazo.themeal.data.model.*
import mx.aplazo.themeal.data.network.MealService
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val api: MealService,
    private val quoteProvider: MealProvider
) {

    suspend fun getAllCategories(): MutableList<Category> {
        val response = api.getAllCategories()
        quoteProvider.categories = response
        return response
    }

    suspend fun getMealsByCategory(category: String): MutableList<MealModel> {
        val response = api.getMealsByCategory(category = category)
        quoteProvider.meals = response
        return response
    }

    suspend fun getDetailMeal(idMeal: String): MealDetail {
        val response = api.getDetailMeal(idMeal = idMeal)
        quoteProvider.detailMeal = response
        return response
    }

    suspend fun getMealSearch(food: String): MutableList<MealDetail> {
        val response = api.getMealSearch(food = food)
        quoteProvider.mealsSearch = response
        return response
    }

    suspend fun getRandomMeal(): MutableList<MealDetail> {
        val response = api.getRandomMeal()
        quoteProvider.mealsRandom = response
        return response
    }

}