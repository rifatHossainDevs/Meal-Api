package com.epsports.mealapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.mealapi.apiService.Service
import com.epsports.mealapi.model.ResponseMealCategoryDetails
import com.epsports.mealapi.model.ResponseMealsCategory
import com.epsports.mealapi.model.ResponseSearchMeal
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _category = MutableLiveData<ResponseMealsCategory>()
    val category: MutableLiveData<ResponseMealsCategory> = _category

    private val _categoryDetails = MutableLiveData<ResponseMealCategoryDetails>()
    val categoryDetails: MutableLiveData<ResponseMealCategoryDetails> = _categoryDetails

    private val _searchMeal = MutableLiveData<ResponseSearchMeal>()
    val searchMeal: MutableLiveData<ResponseSearchMeal> = _searchMeal

    init {
        getCategory()
    }

    private fun getCategory() {
        viewModelScope.launch {
            val response = Service.mealService.getMealCategory()
            _category.value = response.body()
        }
    }

    fun getCategoryDetails(category: String) {
        viewModelScope.launch {
            val detailsResponse = Service.mealService.getMealCategoryDetails(category)
            _categoryDetails.value = detailsResponse.body()
        }
    }

    fun getSearchMeal(mealName: String){
        viewModelScope.launch {
            val response = Service.mealService.getSearchMeal(mealName)
            _searchMeal.value = response.body()
        }
    }
}