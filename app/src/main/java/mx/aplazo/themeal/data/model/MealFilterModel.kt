package mx.aplazo.themeal.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MealFilterModel(
    @SerializedName("meals") val meals: MutableList<MealFilter>
)

data class MealFilter(
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    @SerializedName("idMeal") val idMeal: String,
) : Serializable