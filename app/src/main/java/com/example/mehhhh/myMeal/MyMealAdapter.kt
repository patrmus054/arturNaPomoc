package com.example.mehhhh.myMeal

import android.app.Application
import android.content.Context
import android.util.Log
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
        private var meals = emptyList<MyMeals>()

        inner class MyMealsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mealItemView: TextView = itemView.findViewById(R.id.tv_my_meal)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMealsHolder {
            val itemView = inflater.inflate(R.layout.my_meal_item, parent, false)
            return MyMealsHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyMealsHolder, position: Int) {
            val current = meals[position]
            holder.mealItemView.text = current.title
        }

        internal fun setMeals(meal: List<MyMeals>) {
            this.meals = meal
            notifyDataSetChanged()
        }

        override fun getItemCount() = meals.size

    fun removeAt(position: Int) {
        Log.w("Mye", "1")
        MyMealViewModel(Application()).deleteById(position)
        (meals as ArrayList).removeAt(position)
        notifyItemRemoved(position)
    }
}
