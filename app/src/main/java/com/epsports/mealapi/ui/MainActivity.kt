package com.epsports.mealapi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.epsports.mealapi.adapter.CategoryAdapter
import com.epsports.mealapi.adapter.CategoryDetailsAdapter
import com.epsports.mealapi.databinding.ActivityMainBinding
import com.epsports.mealapi.viewModel.HomeViewModel

class MainActivity : AppCompatActivity(), CategoryAdapter.HandleClickListener,
    CategoryDetailsAdapter.HandleClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: CategoryAdapter
    lateinit var adapter2: CategoryDetailsAdapter
    lateinit var categoryName: String
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observerListener()
        observerListenerDetails()
    }

    private fun observerListenerDetails() {
        homeViewModel.categoryDetails.observe(this) { categoryDetail ->
            try {
                categoryDetail.let {
                    adapter2 = CategoryDetailsAdapter(it.meals, this@MainActivity)
                    binding.rvLayout2.adapter = adapter2
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun observerListener() {
        homeViewModel.category.observe(this) { category ->
            try {
                category.let {
                    adapter = CategoryAdapter(it.categories, this@MainActivity)
                    binding.rvLayout.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getCategoryClickListener(category: String) {
        categoryName = category
        binding.tvCategoryName.text = categoryName
        homeViewModel.getCategoryDetails(categoryName)
    }

    override fun getMealId(mealId: String?) {
        if (!mealId.isNullOrEmpty()) {
            val mealDetailIntent = Intent(this@MainActivity, MealDetailsActivity::class.java)
            mealDetailIntent.putExtra("id", mealId)
            startActivity(mealDetailIntent)
        } else {
            Toast.makeText(this, "Meal ID is missing!", Toast.LENGTH_SHORT).show()
        }
    }

}