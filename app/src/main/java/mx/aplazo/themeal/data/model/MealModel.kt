package mx.aplazo.themeal.data.model

import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    @SerializedName("idMeal") val idMeal: String,
)