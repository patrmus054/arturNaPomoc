package com.example.mehhhh

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.mehhhh.myMeal.MyMealActivity
import com.example.mehhhh.remote.MyMeals
import com.example.mehhhh.remote.RemoteDataSource
import com.example.mehhhh.remote.TMDBResponse
import com.example.mehhhh.remote.TMDBResult
import com.example.mehhhh.ui.home.HomeFragment.Companion.homeViewModel

import kotlinx.android.synthetic.main.activity_meal_detail_activitty.*
import kotlinx.android.synthetic.main.content_meal_detail_activitty.*
import kotlinx.android.synthetic.main.ingredient_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealDetailActivitty : AppCompatActivity() {

    lateinit var meal: TMDBResponse

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail_activitty)

        var job = Job()
        val coroutineScope = CoroutineScope(job + Dispatchers.Main)
        coroutineScope.launch {
            meal = RemoteDataSource().getFullTMDBMealDetailsById(homeViewModel.getMealId()!!)
            tv_meal_id.text = tv_meal_id.text.toString() + meal.meals[0].idMeal
            tv_meal_name.text = tv_meal_name.text.toString() + meal.meals[0].strMeal
            tv_meal_category.text = tv_meal_category.text.toString() + meal.meals[0].strCategory
            tv_meal_area.text = tv_meal_area.text.toString() + meal.meals[0].strArae
            tv_meal_instruction.text = tv_meal_instruction.text.toString() +  meal.meals[0].strInstrumentation
            tv_meal_tags.text =  tv_meal_tags.text.toString() + meal.meals[0].strTags
            tv_meal_youtube.text = tv_meal_youtube.text.toString() + meal.meals[0].strYoutube
            tv_meal_ingredients.text = tv_meal_ingredients.text.toString() + "1. " + meal.meals[0].strIngredient1 + "2. " + meal.meals[0].strIngredient2 + "3. " + meal.meals[0].strIngredient3 + "4. " + meal.meals[0].strIngredient4 + "5. " + meal.meals[0].strIngredient5 + "6. " + meal.meals[0].strIngredient6 + ""
//
//
//        private val newWordActivityRequestCode = 1
//
//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode, resultCode, data)
//            if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//
//                val word = MyMeals()
//                word.title = tv_meal_name.text.toString()
//                MyMealActivity.mealViewModel.insert(word)
//
//            } else {
//                Toast.makeText(
//                    applicationContext,
//                    "Cannot be saved",
//                    Toast.LENGTH_LONG
//                ).show()
//
//            }
//        }
        }

        bt_favorites.setOnClickListener {
                    val replyIntent = Intent()
                        val meal = tv_meal_name.text.toString()
                        replyIntent.putExtra(EXTRA_REPLY, meal)
                        setResult(Activity.RESULT_OK, replyIntent)
                    }
            var intent = Intent(this, MyMealActivity::class.java)
            startActivity(intent)
            }

            companion object {
            const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        }



}
//
//class MealDetailActivity : AppCompatActivity() {
//
//    lateinit var meal: TMDBResponse
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_meal_detail_activitty)
//
//        var job = Job()
//        val coroutineScope = CoroutineScope(job + Dispatchers.Main)
//        coroutineScope.launch {
//            meal = RemoteDataSource().getTMDBMealsByIngredient()
//            tv_meal_id.text = tv_meal_id.text.toString() + meal.meals[0].idMeal
//            tv_meal_name.text = tv_meal_name.text.toString() + meal.meals[0].strMeal
//            tv_meal_category.text = tv_meal_category.text.toString() + meal.meals[0].strCategory
//            tv_meal_area.text = tv_meal_area.text.toString() + meal.meals[0].strArae
//            tv_meal_instruction.text = tv_meal_instruction.text.toString() +  meal.meals[0].strInstrumentation
//            tv_meal_tags.text =  tv_meal_tags.text.toString() + meal.meals[0].strTags
//            tv_meal_youtube.text = tv_meal_youtube.text.toString() + meal.meals[0].strYoutube
//            tv_meal_ingredients.text = tv_meal_ingredients.text.toString() + "1. " + meal.meals[0].strIngredient1 + "2. " + meal.meals[0].strIngredient2 + "3. " + meal.meals[0].strIngredient3 + "4. " + meal.meals[0].strIngredient4 + "5. " + meal.meals[0].strIngredient5 + "6. " + meal.meals[0].strIngredient6 + ""
//        }
//
//
//
//    }
//
//}
