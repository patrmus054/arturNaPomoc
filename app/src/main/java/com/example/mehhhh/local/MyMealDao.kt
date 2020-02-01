package com.example.mehhhh.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mehhhh.remote.MyMeals


@Dao
interface MyMealDao {

    @Query("SELECT * from my_meals ORDER BY title")
    fun getMyMeals(): LiveData<List<MyMeals>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meal: MyMeals)

    @Query("DELETE FROM my_meals")
    suspend fun deleteAll()
}