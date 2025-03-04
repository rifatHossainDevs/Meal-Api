package com.epsports.mealapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.epsports.mealapi.adapter.CategoryAdapter
import com.epsports.mealapi.adapter.CategoryDetailsAdapter
import com.epsports.mealapi.databinding.ActivityMainBinding
import com.epsports.mealapi.viewModel.HomeViewModel

class MainActivity : AppCompatActivity(), CategoryAdapter.HandleClickListener {
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
        homeViewModel.categoryDetails.observe(this){categoryDetail->
            try {
                categoryDetail.let {
                    adapter2 = CategoryDetailsAdapter(it.meals)
                    binding.rvLayout2.adapter = adapter2
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun observerListener() {
        homeViewModel.category.observe(this){category->
            try {
                category.let {
                    adapter = CategoryAdapter(it.categories, this@MainActivity)
                    binding.rvLayout.adapter = adapter
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun getCategoryClickListener(category: String) {
        categoryName = category
        binding.tvCategoryName.text = categoryName
        homeViewModel.getCategoryDetails(categoryName)
    }
}