package mx.aplazo.themeal.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryModel(
    @SerializedName("categories") val categories: MutableList<Category>
)

data class Category(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String
) : Serializable