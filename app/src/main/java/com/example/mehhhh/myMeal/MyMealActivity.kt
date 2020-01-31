package com.example.mehhhh.myMeal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        setContentView(R.layout.activity_my_meal)
        val recyclerView = findViewById<RecyclerView>(R.id.my_meal_recycle_view)
        val adapter = MyMealAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mealViewModel = ViewModelProvider(this).get(MyMealViewModel::class.java)
        mealViewModel.allMeals.observe(this, Observer { meal ->
            // Update the cached copy of the words in the adapter.
            meal?.let { adapter.setWords(it) }
        })

    }

    private val newWordActivityRequestCode = 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(MealDetailActivitty.EXTRA_REPLY)?.let {
                val meal = MyMeals()
                meal.title
                mealViewModel.insert(meal)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "nie da sie",
                Toast.LENGTH_LONG).show()
        }

    }
}
