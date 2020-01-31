package com.example.mehhhh.local

import com.example.mehhhh.DataSource
import com.example.mehhhh.remote.*

class LocalDataSource: DataSource {
    override suspend fun getAllMeals(): List<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMealsByName(name: String): List<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMealsByIngredient(ingredient: String): List<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMealsByIngredientAndName(
        name: String,
        ingredient: String
    ): List<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun refreshNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTMDBMealByName(name: String): TMDBResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getFullTMDBMealDetailsById(id: String): TMDBResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getSingleTMDBMeal(): List<TMDBResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllTMDBMealCategories(): List<TMDBCategoriesRespond> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getListOfCategories(category: String): TMDBResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getListOfArea(area: String): TMDBResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getListOfIngedients(list: String): List<TMDBIngredients> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTMDBMealsByCategory(category: String): TMDBResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTMDBMealsByArea(area: String): TMDBResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTMDBMealsByIngredient(ingredient: String): TMDBMealByIngredientRespond {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}