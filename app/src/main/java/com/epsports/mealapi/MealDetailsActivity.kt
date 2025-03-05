package com.epsports.mealapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.epsports.mealapi.databinding.ActivityMealDetailsBinding
import com.epsports.mealapi.viewModel.MealDetailsViewModel

class MealDetailsActivity : AppCompatActivity() {
    private val viewModel: MealDetailsViewModel by viewModels()
    private lateinit var binding: ActivityMealDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mealId = intent.getStringExtra("id")
        if (mealId != null) {
            viewModel.getMealDetails(mealId)
        }

        observer()
    }

    @SuppressLint("SetTextI18n")
    private fun observer() {
        viewModel.mealDetails.observe(this) { mealDetails ->
            mealDetails?.meals?.firstOrNull()?.let { meal ->
                with(binding) {
                    tvMealName.text = meal.strMeal
                    tvMealCategory.text = "Category: ${meal.strCategory}"
                    tvMealOriginCountry.text = "Origin Country: ${meal.strArea}"
                    tvMealTags.text = "Tags: ${meal.strTags}"
                    tvMealIngredients.text =
                        "Ingredients: ${meal.strIngredient1}, ${meal.strIngredient2}, ${meal.strIngredient3}, ${meal.strIngredient4}, ${meal.strIngredient5}, ${meal.strIngredient6}, ${meal.strIngredient7}"
                    tvMealInstruction.text = "Instructions: ${meal.strInstructions}"

                    ivMealImage.load(meal.strMealThumb)
                    ivWatchOnYoutube.setOnClickListener {
                        val watchIntent = Intent(this@MealDetailsActivity, MainActivity::class.java)
                        watchIntent.putExtra("link", meal.strYoutube)
                        startActivity(watchIntent)
                    }
                }
            }
        }
    }
}