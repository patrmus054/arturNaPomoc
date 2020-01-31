package com.example.mehhhh.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("api/")
    fun getCurrentMealData(@Query("i") ingredient: String, @Query("q") meal: String, @Query("p") page: Int): Call<MealResponse>

    @GET("search.php/")
    fun getTMDBMealByName(@Query("s")name: String): Call<TMDBResponse>

    @GET("lookup.php/")
    fun getFullTMDBMealDetailsById(@Query("i") id: String): Call<TMDBResponse>

    @GET("random.php/")
    fun getSingleTMDBMeal(): Call<TMDBResponse>

    @GET("categories.php/")
    fun getAllTMDBMealCategories(): Call<ArrayList<TMDBCategoriesRespond>>

    @GET("list.php/")
    fun getListOfCategories(@Query("c")category: String): Call<TMDBResponse>

    @GET("list.php/")
    fun getListOfArea(@Query("a")area: String): Call<TMDBResponse>

    @GET("list.php/")
    fun getListOfIngedients(@Query("i")i: String): Call<TMDBIngredientsRespond>

    @GET("filter.php/")
    fun getTMDBMealsByCategory(@Query("c")category: String): Call<TMDBResponse>

    @GET("filter.php/")
    fun getTMDBMealsByIngredient(@Query("i")ingredient: String): Call<TMDBMealByIngredientRespond>

    @GET("filter.php")
    fun getTMDBMealsByArea(@Query("a")area: String): Call<TMDBResponse>




}