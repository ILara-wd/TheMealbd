package mx.aplazo.themeal.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealProvider @Inject constructor() {
    var categories: List<Category> = emptyList()
    var meals: List<MealFilter> = emptyList()
    var detailMeal: MealDetail? = null
    var mealsSearch: List<MealDetail> = emptyList()
    var mealsRandom: List<MealDetail> = emptyList()
}