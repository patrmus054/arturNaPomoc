package com.example.mehhhh.local

import android.content.Context
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

    companion object{
        @Volatile
        private var INSTANCE: MyMealDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MyMealDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   MyMealDatabase::class.java,
                   "my_meals"
               ).addCallback(MealDatabaseCallback(scope))
                   .build()
                INSTANCE = instance
                return instance
            }
        }
    }
    private class MealDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database ->
                scope.launch {
                    var mealDao = database.moyMealDao()

                    mealDao.deleteAll()
                    var mMeal = MyMeals()
                    mMeal.title = "Zupa"
                    mealDao.insert(mMeal)
                    mMeal.title = "Kluski"
                    mealDao.insert(mMeal)

                }

            }
        }
    }
}
