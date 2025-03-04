package com.epsports.mealapi.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseMealsCategory(
    @SerializedName("categories")
    val categories: List<Category>? = null
){
    @Keep
    data class Category(
        @SerializedName("idCategory")
        val idCategory: String? = null,
        @SerializedName("strCategory")
        val strCategory: String? = null,
        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String? = null,
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String? = null
    )
}