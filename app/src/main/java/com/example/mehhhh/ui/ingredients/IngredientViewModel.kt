package com.example.mehhhh.ui.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehhhh.Repository
import com.example.mehhhh.local.LocalDataSource
import com.example.mehhhh.remote.RemoteDataSource
import com.example.mehhhh.remote.TMDBIngredients
import com.example.mehhhh.remote.TMDBIngredientsRespond
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IngredientViewModel : ViewModel() {

    companion object{
        var isInternet: Boolean = true
        lateinit var mIngredient: TMDBIngredients

    }

    fun getIngredientId(): String? = mIngredient.id
    fun setIngredient(ingredient: TMDBIngredients){
        mIngredient = ingredient
    }
    private  var viewModelJob = Job()
    private val coroutinesScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    private  var _item: MutableLiveData<List<TMDBIngredients>> = MutableLiveData()

    val item: LiveData<List<TMDBIngredients>>
    get() = _item

    fun getAllIngredients(){
        coroutinesScope.launch {
            _item.value = getData().getListOfIngedients("list")
        }
    }

    fun getData(): Repository = if(isInternet) Repository(RemoteDataSource()) else Repository( LocalDataSource())


    private val _shouldShowList = MutableLiveData<Boolean?>()
    val shouldShowList: LiveData<Boolean?>
        get() = _shouldShowList

    fun showList(){
        _shouldShowList.value = true
    }
}
