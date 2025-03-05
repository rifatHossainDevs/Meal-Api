package com.epsports.mealapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.epsports.mealapi.databinding.CategoryItemDetailsBinding
import com.epsports.mealapi.model.ResponseMealCategoryDetails

class CategoryDetailsAdapter(
    private val categoryDetailsList: List<ResponseMealCategoryDetails.Meal?>?,
    private val listener: HandleClickListener
) : RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailViewHolder>() {

    interface HandleClickListener {
        fun getMealId(mealId: String?)
    }

    class CategoryDetailViewHolder(val binding: CategoryItemDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        return CategoryDetailViewHolder(
            CategoryItemDetailsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = categoryDetailsList?.size ?: 0

    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        categoryDetailsList?.get(position)?.let { categoryDetails ->
            holder.binding.apply {
                ivDetail.load(categoryDetails.strMealThumb)
                tvName.text = categoryDetails.strMeal

                fullLayout.setOnClickListener {
                    categoryDetails.idMeal?.let { mealId -> listener.getMealId(mealId) }
                }
            }
        }
    }


}