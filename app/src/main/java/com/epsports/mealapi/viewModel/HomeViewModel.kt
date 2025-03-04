package com.epsports.mealapi.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.mealapi.apiService.Service
import com.epsports.mealapi.model.ResponseMealCategoryDetails
import com.epsports.mealapi.model.ResponseMealsCategory
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _category = MutableLiveData<ResponseMealsCategory>()
    val category: MutableLiveData<ResponseMealsCategory> = _category

    private val _categoryDetails = MutableLiveData<ResponseMealCategoryDetails>()
    val categoryDetails: MutableLiveData<ResponseMealCategoryDetails> = _categoryDetails

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

}