package com.example.mehhhh

import com.example.mehhhh.remote.*

class Repository(private val dataSource: DataSource): DataSource{

    override suspend fun getAllMeals(): List<Result> = dataSource.getAllMeals()

    override suspend fun getMealsByName(name: String): List<Result> = dataSource.getMealsByName(name)

    override suspend fun getMealsByIngredient(ingredient: String): List<Result> = dataSource.getMealsByIngredient(ingredient)

    override suspend fun getMealsByIngredientAndName(name: String, ingredient: String): List<Result> = dataSource.getMealsByIngredientAndName(name, ingredient)

    override suspend fun refreshNews() = dataSource.refreshNews()
    override suspend fun getTMDBMealByName(name: String): TMDBResponse = dataSource.getTMDBMealByName(name)

    override suspend fun getFullTMDBMealDetailsById(id: String): TMDBResponse  = dataSource.getFullTMDBMealDetailsById(id)

    override suspend fun getSingleTMDBMeal(): List<TMDBResponse> = dataSource.getSingleTMDBMeal()

    override suspend fun getAllTMDBMealCategories(): List<TMDBCategoriesRespond> = dataSource.getAllTMDBMealCategories()

    override suspend fun getListOfCategories(category: String): TMDBResponse = dataSource.getListOfCategories(category)

    override suspend fun getListOfArea(area: String): TMDBResponse = dataSource.getListOfArea(area)

    override suspend fun getListOfIngedients(list: String): List<TMDBIngredients> = dataSource.getListOfIngedients(list)

    override suspend fun getTMDBMealsByCategory(category: String): TMDBResponse = dataSource.getTMDBMealsByCategory(category)

    override suspend fun getTMDBMealsByArea(area: String): TMDBResponse = dataSource.getTMDBMealsByArea(area)
    override suspend fun getTMDBMealsByIngredient(ingredient: String): TMDBMealByIngredientRespond  = dataSource.getTMDBMealsByIngredient(ingredient)
}