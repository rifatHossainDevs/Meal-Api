package com.epsports.mealapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.epsports.mealapi.databinding.CategoryItemDetailsBinding
import com.epsports.mealapi.model.ResponseMealCategoryDetails

class CategoryDetailsAdapter(private val categoryDetailsList: List<ResponseMealCategoryDetails.Meal?>?): RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailViewHolder>() {

    class CategoryDetailViewHolder(val binding: CategoryItemDetailsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        return CategoryDetailViewHolder(CategoryItemDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = categoryDetailsList!!.size

    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        val categoryDetails = categoryDetailsList?.get(position)

        holder.binding.apply {
            if (categoryDetails != null) {
                ivDetail.load(categoryDetails.strMealThumb)
            }
            if (categoryDetails != null) {
                tvName.text = categoryDetails.strMeal
            }
        }
    }


}