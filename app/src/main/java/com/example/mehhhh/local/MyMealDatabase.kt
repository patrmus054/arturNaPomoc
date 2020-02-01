package com.example.mehhhh.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mehhhh.remote.MyMeals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(MyMeals::class), version = 1, exportSchema = false)
public abstract class MyMealDatabase: RoomDatabase() {

    abstract fun moyMealDao(): MyMealDao

    companion object {
        @Volatile
        private var INSTANCE: MyMealDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MyMealDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                Log.w("bub", "jest")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyMealDatabase::class.java,"my_meals")
                    .addCallback(MealDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                Log.w("bub","2")
                INSTANCE = instance
                // return instance
                instance
            }
        }
        private class MealDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
//                        var wordDao = database.moyMealDao()
//
//                        // Delete all content here.
//                        wordDao.deleteAll()
//
//                        // Add sample words.
//                        var meal = MyMeals()
//                        meal.title = "zupa"
//                        wordDao.insert(meal)

                    }
                }
            }
        }

    }
}