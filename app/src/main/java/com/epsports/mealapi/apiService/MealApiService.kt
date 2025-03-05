package com.epsports.mealapi.apiService

import com.epsports.mealapi.model.ResponseMealCategoryDetails
import com.epsports.mealapi.model.ResponseMealDetails
import com.epsports.mealapi.model.ResponseMealsCategory
import com.epsports.mealapi.model.ResponseSearchMeal
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("categories.php")
    suspend fun getMealCategory(): Response<ResponseMealsCategory>

    @GET("filter.php")
    suspend fun getMealCategoryDetails(
        @Query("c") category: String
    ): Response<ResponseMealCategoryDetails>

    @GET("lookup.php")
    suspend fun getMealDetailsById(
        @Query("i") mealId: String
    ): Response<ResponseMealDetails>

    @GET("search.php")
    suspend fun getSearchMeal(
        @Query("s") mealName: String
    ): Response<ResponseSearchMeal>
}

object Service {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mealService: MealApiService = retrofit.create(MealApiService::class.java)
}