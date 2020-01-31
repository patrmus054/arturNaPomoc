package com.example.mehhhh.myMeal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mehhhh.R
import com.example.mehhhh.remote.MyMeals

class MyMealAdapter internal constructor(
    context: Context
):RecyclerView.Adapter<MyMealAdapter.MyMealsHolder>(){
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var meals = emptyList<MyMeals>() // Cached copy of words

        inner class MyMealsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mealItemView: TextView = itemView.findViewById(R.id.textView)
                //
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMealsHolder {
            val itemView = inflater.inflate(R.layout.my_meal_item, parent, false)
            return MyMealsHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyMealsHolder, position: Int) {
            val current = meals[position]
            holder.mealItemView.text = current.title
        }

        internal fun setWords(words: List<MyMeals>) {
            this.meals = words
            notifyDataSetChanged()
        }

        override fun getItemCount() = meals.size
    }
