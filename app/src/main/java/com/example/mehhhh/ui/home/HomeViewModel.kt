package com.example.mehhhh.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehhhh.Repository
import com.example.mehhhh.local.LocalDataSource
import com.example.mehhhh.remote.*
import com.example.mehhhh.ui.ingredients.IngredientViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    companion object{
        var isInternet: Boolean = true
        lateinit var mMeal: TMDBResult
    }

    fun setMeal(meal: TMDBResult){
        mMeal = meal
    }

    fun getMealId(): String? = mMeal.idMeal
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )


    private var _item: MutableLiveData<List<TMDBResult>> = MutableLiveData()
    private var _itemList: MutableLiveData<List<TMDBMealByIngredinet>> = MutableLiveData()

    val item: LiveData<List<TMDBResult>>
        get() = _item

    val itemList: MutableLiveData<List<TMDBMealByIngredinet>>
        get() = _itemList

    private val _shouldShowDetails = MutableLiveData<Boolean?>()
    val shouldShowDetails: LiveData<Boolean?>
        get() = _shouldShowDetails

    private val _shouldShowListDetails = MutableLiveData<Boolean?>()
    val shouldShowListDetails: LiveData<Boolean?>
        get() = _shouldShowDetails

    fun showDetail(){
        _shouldShowDetails.value = true
    }

    fun showList(){
        _shouldShowListDetails.value = true
    }


    fun getAllMeals(){
        coroutineScope.launch {
            Log.e("myapp","weszlo")
            val prepareFoods = mutableListOf<TMDBResult>()
            for(i in 0..10) {
                val result = getData().getSingleTMDBMeal()[0].meals
                prepareFoods.add(result[0])
            }
            _item.value = prepareFoods
        }
    }

    fun getMealsByIngredientId(ingredient: String){

        coroutineScope.launch {
            _itemList.value = getData().getTMDBMealsByIngredient(ingredient).meals
        }
    }




    fun getData(): Repository = if (isInternet) Repository( RemoteDataSource()) else Repository(  LocalDataSource()  )

}