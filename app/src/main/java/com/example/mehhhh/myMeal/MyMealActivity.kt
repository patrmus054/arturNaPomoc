package com.example.mehhhh.myMeal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mehhhh.MealDetailActivitty
import com.example.mehhhh.R
import com.example.mehhhh.remote.MyMeals


class MyMealActivity : AppCompatActivity() {

    companion object{
        lateinit var mealViewModel: MyMealViewModel
        val swipeHandler = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_meal)
        var recyclerView = findViewById<RecyclerView>(R.id.my_meal_recycle_view)
        val adapter = MyMealAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mealViewModel = ViewModelProvider(this).get(MyMealViewModel::class.java)
        mealViewModel.allMeals.observe(this, Observer { meals ->
            meals?.let { adapter.setMeals(it) }
        })


        if(intent.extras != null){
            var it = intent.extras?.getString(MealDetailActivitty.EXTRA_REPLY)
            val meal = MyMeals()
            meal.title = it!!
            mealViewModel.insert(meal)
            Toast.makeText(applicationContext,"Dodano skladnik do bazy", Toast.LENGTH_LONG).show()
        }


        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as MyMealAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(MealDetailActivitty.EXTRA_REPLY)?.let {
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

    abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_foreground)
        private val intrinsicWidth = deleteIcon?.intrinsicWidth
        private val intrinsicHeight = deleteIcon?.intrinsicHeight
        private val background = ColorDrawable()
        private val backgroundColor = Color.parseColor("#f44336")
        private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }


        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
            /**
             * To disable "swipe" for specific item return 0 here.
             * For example:
             * if (viewHolder?.itemViewType == YourAdapter.SOME_TYPE) return 0
             * if (viewHolder?.adapterPosition == 0) return 0
             */
            if (viewHolder.adapterPosition == 10) return 0
            return super.getMovementFlags(recyclerView, viewHolder)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: ViewHolder,
            target: ViewHolder
        ): Boolean {
            return false
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {

            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top
            val isCanceled = dX == 0f && !isCurrentlyActive

            if (isCanceled) {
                clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                super.onChildDraw(c,
                    recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                return
            }

            // Draw the red delete background
            background.color = backgroundColor
            background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            background.draw(c)

            // Calculate position of delete icon
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight!!) / 2
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth!!
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight

            // Draw the delete icon
            deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            deleteIcon?.draw(c)

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
            c?.drawRect(left, top, right, bottom, clearPaint)
        }

    }

}
