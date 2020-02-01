package com.example.mehhhh.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehhhh.MealDetailActivitty
import com.example.mehhhh.R
import com.example.mehhhh.remote.Result
import com.example.mehhhh.remote.TMDBMealByIngredinet
import com.example.mehhhh.remote.TMDBResult
import com.example.mehhhh.ui.home.ListAdapter
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    companion object{
        fun newInstance(): SearchFragment = SearchFragment()
        lateinit var searchViewModel: SearchViewModel

    }

    var text: String = ""
    lateinit var listAdapter: ListAdapterV2

    var mMealList: List<TMDBMealByIngredinet>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?):
            View? {searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        text = arguments!!.getString("name").toString()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mMealList = mutableListOf()
        listAdapter = ListAdapterV2(mMealList as MutableList<TMDBResult>)
        search_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }
        searchViewModel.item.observe(viewLifecycleOwner, Observer {
            Log.e("myapp","Working...")

            listAdapter.setList(it)
        })
        Log.w("thisssss", text)
        searchViewModel.getMealsByName(text)
        searchViewModel.shouldShowDetails.observe(viewLifecycleOwner, Observer {
            var intent = Intent(activity, MealDetailActivitty::class.java)
            intent.putExtra("FRAGMENT", "SearchFragment")
            startActivity(intent)
        })

    }



}
