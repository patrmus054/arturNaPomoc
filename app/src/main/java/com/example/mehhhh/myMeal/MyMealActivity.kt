package com.example.mehhhh.myMeal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mehhhh.MealDetailActivitty
import com.example.mehhhh.R
import com.example.mehhhh.remote.MyMeals

class MyMealActivity : AppCompatActivity() {

    companion object{
        lateinit var mealViewModel: MyMealViewModel
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("Mm", "1")
        setContentView(R.layout.activity_my_meal)
        val recyclerView = findViewById<RecyclerView>(R.id.my_meal_recycle_view)
        Log.w("Mm", "2")
        val adapter = MyMealAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mealViewModel = ViewModelProvider(this).get(MyMealViewModel::class.java)
        Log.w("Mm", "3")

        mealViewModel.allMeals.observe(this, Observer { meals ->
            meals?.let { adapter.setMeals(it) }
            meals.forEach{meal -> Log.w("Mm", meal.title)}


        })


            var it = intent.extras?.getString(MealDetailActivitty.EXTRA_REPLY)
            Log.w("MyMealActivity", "no witam")
            val meal = MyMeals()
            meal.title = it!!
            mealViewModel.insert(meal)


            Toast.makeText(applicationContext,"Dodano skladnik do bazy", Toast.LENGTH_LONG).show()
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i("MyMealActivity", requestCode.toString() + " " + Activity.RESULT_OK)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(MealDetailActivitty.EXTRA_REPLY)?.let {
                Log.w("MyMealActivity", "no witam")
                val meal = MyMeals()
                meal.title = it
                mealViewModel.insert(meal)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Nie mozna dodac do bazy",
                Toast.LENGTH_LONG).show()
        }

    }
}
