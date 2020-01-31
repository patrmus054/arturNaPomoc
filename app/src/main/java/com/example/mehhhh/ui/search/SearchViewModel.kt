package com.example.mehhhh.ui.search

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehhhh.Repository
import com.example.mehhhh.local.LocalDataSource
import com.example.mehhhh.remote.RemoteDataSource
import com.example.mehhhh.remote.Result
import com.example.mehhhh.remote.TMDBResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    companion object{
        var isInternet: Boolean = true
        lateinit var mMeal: TMDBResult
    }


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    var _item: MutableLiveData<List<TMDBResult>>
    init {
        _item = MutableLiveData()
    }
    val item:LiveData<List<TMDBResult>>
        get() = _item



    fun getMealsByName(name: String){
        coroutineScope.launch {
            Log.e("myapp","weszlo")
            val result = getData().getTMDBMealByName(name)
            _item.value = result.meals
        }
    }

    fun getData(): Repository = if (isInternet) Repository(RemoteDataSource()) else Repository(
        LocalDataSource()
    )


    fun setMeal(meal: TMDBResult){
        mMeal = meal
    }

    private val _shouldShowDetails = MutableLiveData<Boolean?>()
    val shouldShowDetails: LiveData<Boolean?>
        get() = _shouldShowDetails

    fun showDetail(){
        _shouldShowDetails.value = true
    }

    fun getAllMeals(){
        coroutineScope.launch {
            Log.e("myapp","weszlo")
            val prepareFoods = mutableListOf<TMDBResult>()
            for(i in 0..10) {
                val result = getData().getSingleTMDBMeal()[0].meals
                prepareFoods.add(result[0])
            }
            //Log.w("HomeViewModel", result[0].strMeal)
            _item.value = prepareFoods
        }
    }

}




