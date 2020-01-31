package com.example.mehhhh

import com.example.mehhhh.remote.*

interface DataSource {

    suspend fun getAllMeals(): List<Result>
    suspend fun getMealsByName(name: String): List<Result>
    suspend fun getMealsByIngredient(ingredient: String): List<Result>
    suspend fun getMealsByIngredientAndName(name: String, ingredient: String): List<Result>
    suspend fun refreshNews()
    suspend fun getTMDBMealByName(name: String): TMDBResponse

    suspend fun getFullTMDBMealDetailsById(id: String): TMDBResponse

    suspend fun getSingleTMDBMeal(): List<TMDBResponse>

    suspend fun getAllTMDBMealCategories(): List<TMDBCategoriesRespond>

    suspend fun getListOfCategories(category: String): TMDBResponse

    suspend fun getListOfArea(area: String): TMDBResponse

    suspend fun getListOfIngedients(list: String): List<TMDBIngredients>

    suspend fun getTMDBMealsByCategory(category: String): TMDBResponse

    suspend fun getTMDBMealsByArea(area: String): TMDBResponse

    suspend fun getTMDBMealsByIngredient(ingredient: String): TMDBMealByIngredientRespond


}