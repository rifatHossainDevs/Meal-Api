package com.epsports.mealapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.epsports.mealapi.databinding.CategoryItemBinding
import com.epsports.mealapi.model.ResponseMealsCategory

class CategoryAdapter(
    private val categoryList: List<ResponseMealsCategory.Category?>?,
    private val listener: HandleClickListener,
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface HandleClickListener {
        fun getCategoryClickListener(category: String)
    }

    class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoryList!!.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList?.get(position)

        holder.binding.apply {
            ivLogo.load(category?.strCategoryThumb)
            tvCategoryName.text = category?.strCategory

            fullLayout.setOnClickListener {
                if (category != null) {
                    category.strCategory?.let { it1 -> listener.getCategoryClickListener(it1) }
                }
            }
        }
    }

}