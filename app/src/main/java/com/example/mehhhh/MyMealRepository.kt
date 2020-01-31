package com.example.mehhhh

import androidx.lifecycle.LiveData
import com.example.mehhhh.local.MyMealDao
import com.example.mehhhh.remote.MyMeals

class MyMealRepository(private val myMealDao: MyMealDao) {
    val allMeals : LiveData<List<MyMeals>> = myMealDao.getMyMeals()

    suspend fun insert(myMeals: MyMeals){
        myMealDao.insert(myMeals)
    }
}