package com.example.mehhhh.myMeal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mehhhh.local.MyMealDatabase
import com.example.mehhhh.remote.MyMeals
import kotlinx.coroutines.launch

class MyMealViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MyMealRepository

    val allMeals: LiveData<List<MyMeals>>

    init {

        val myMealDao = MyMealDatabase.getDatabase(application, viewModelScope).moyMealDao()
        repository = MyMealRepository(myMealDao)
        allMeals = repository.allMeals
    }

    fun insert(meal: MyMeals) = viewModelScope.launch {
        repository.insert(meal)
    }

    fun deleteById(id: Int) = viewModelScope.launch{
        repository.deleteAtId(id)
    }
}