package com.example.mehhhh.ui.ingredients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehhhh.Repository
import com.example.mehhhh.local.LocalDataSource
import com.example.mehhhh.remote.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    companion object{
        var isInternet: Boolean = true
        lateinit var mMeals: TMDBMealByIngredinet
        fun setMeal(meals: TMDBMealByIngredinet){
            mMeals = meals
        }
    }

    fun getMealId(): String? = mMeals.id

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )


    private var _item: MutableLiveData<List<TMDBMealByIngredinet>> = MutableLiveData()

    val item: LiveData<List<TMDBMealByIngredinet>>
        get() = _item

    private val _shouldShowDetails = MutableLiveData<Boolean?>()
    val shouldShowDetails: LiveData<Boolean?>
        get() = _shouldShowDetails

    fun showList(){
        _shouldShowDetails.value = true
    }

    fun getMealsByIngredient(){
        coroutineScope.launch {
            _item.value = getData().getTMDBMealsByIngredient(IngredientViewModel.mIngredient.id.toString()).meals
        }
    }



    fun getData(): Repository = if (isInternet) Repository( RemoteDataSource()) else Repository(  LocalDataSource()  )

}
