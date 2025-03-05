package com.epsports.mealapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.mealapi.apiService.Service
import com.epsports.mealapi.model.ResponseMealDetails
import kotlinx.coroutines.launch

class MealDetailsViewModel: ViewModel() {

    private val _mealDetails = MutableLiveData<ResponseMealDetails>()
    val mealDetails: MutableLiveData<ResponseMealDetails> = _mealDetails

    fun getMealDetails(mealId: String){
        viewModelScope.launch {
            val mealDetailsResponse = Service.mealService.getMealDetailsById(mealId)
            _mealDetails.value = mealDetailsResponse.body()
        }
    }

}