package com.epsports.mealapi.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseMealCategoryDetails(
    @SerializedName("meals")
    val meals: List<Meal?>? = null,
) {
    @Keep
    data class Meal(
        @SerializedName("idMeal")
        val idMeal: String? = null,
        @SerializedName("strMeal")
        val strMeal: String? = null,
        @SerializedName("strMealThumb")
        val strMealThumb: String? = null,
    )
}