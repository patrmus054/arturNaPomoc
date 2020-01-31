package com.example.mehhhh.remote

import android.util.Log
import com.example.mehhhh.DataSource
import com.example.mehhhh.Repository
import com.example.mehhhh.ui.search.SearchViewModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource: DataSource {

    companion object{
        val BaseUrl = "http://www.recipepuppy.com/"
        val TMDBBaseUrl = "https://www.themealdb.com/api/json/v1/1/"
        var page = 1
    }

    override suspend fun getAllMeals(): List<Result> = getData("","", page)

    override suspend fun getMealsByName(name: String): List<Result> = getData("", name, page)

    override suspend fun getMealsByIngredient(ingredient: String): List<Result> = getData(ingredient, "", page)

    override suspend fun getMealsByIngredientAndName(name: String, ingredient: String): List<Result> = getData(ingredient,name,page)

    override suspend fun refreshNews(){ getAllMeals() }
    override suspend fun getTMDBMealByName(name: String): TMDBResponse {

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getTMDBMealByName(name)

        return call.await()
    }

    override suspend fun getFullTMDBMealDetailsById(id: String): TMDBResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getFullTMDBMealDetailsById(id)
        return call.await()
    }

    override suspend fun getSingleTMDBMeal(): List<TMDBResponse> {

        Log.w("RemoteDataSource", "Jestem tu")
        var mealsData = mutableListOf<TMDBResponse>()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)


            Log.w("RemoteDataSource", "Jestem tu i tu")
            val call = service.getSingleTMDBMeal()
            var result = call.await()

                mealsData.add(result)


        return mealsData
    }

    override suspend fun getAllTMDBMealCategories(): List<TMDBCategoriesRespond> {
        val retrofit = Retrofit.Builder()
        .baseUrl(TMDBBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getAllTMDBMealCategories()
        return call.await()
    }

    override suspend fun getListOfCategories(category: String): TMDBResponse {
        val retrofit = Retrofit.Builder()
        .baseUrl(TMDBBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getListOfCategories(category)
        return call.await()
    }

    override suspend fun getListOfArea(area: String): TMDBResponse {
        val retrofit = Retrofit.Builder()
        .baseUrl(TMDBBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getListOfArea(area)
        return call.await()
    }

    override suspend fun getListOfIngedients(list: String): List<TMDBIngredients> {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getListOfIngedients(list)
        var result =  call.await()
        return result.ingredeints
    }

    override suspend fun getTMDBMealsByCategory(category: String): TMDBResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getTMDBMealsByCategory(category)
        return call.await()
    }

    override suspend fun getTMDBMealsByArea(area: String): TMDBResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getTMDBMealsByArea(area)
        return call.await()
    }

    override suspend fun getTMDBMealsByIngredient(ingredient: String): TMDBMealByIngredientRespond {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getTMDBMealsByIngredient(ingredient)
        return call.await()
    }

    private suspend fun getData(ingredient: String, meal: String, page: Int) : List<Result>{

        var mealsData: MealResponse? = null

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealService::class.java)
        val call = service.getCurrentMealData("","", Companion.page)

        var result = call.await()
        return result.results
    }
}