package mx.aplazo.themeal.data.network

import mx.aplazo.themeal.data.model.CategoryModel
import mx.aplazo.themeal.data.model.MealDetailsModel
import mx.aplazo.themeal.data.model.MealFilterModel
import mx.aplazo.themeal.data.model.MealModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiClient {

    @GET("categories.php")
    suspend fun getAllCategories(): Response<CategoryModel>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") c: String): Response<MealFilterModel>

    @GET("lookup.php")
    suspend fun getDetailMeal(@Query("i") i: String): Response<MealDetailsModel>

    @GET("search.php")
    suspend fun getMealSearchLetter(@Query("f") f: String): Response<MealDetailsModel>

    @GET("search.php")
    suspend fun getMealSearchByName(@Query("s") s: String): Response<MealDetailsModel>


    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealDetailsModel>

}