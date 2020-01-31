package com.example.mehhhh.ui.ingredients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehhhh.MealDetailActivitty
import com.example.mehhhh.R
import com.example.mehhhh.remote.RemoteDataSource
import com.example.mehhhh.remote.Result
import com.example.mehhhh.remote.TMDBMealByIngredinet
import com.example.mehhhh.remote.TMDBResult
import com.example.mehhhh.ui.home.HomeFragment
import com.example.mehhhh.ui.home.HomeViewModel
import com.example.mehhhh.ui.home.ListAdapter
import com.example.mehhhh.ui.home.MainActivity
import com.example.mehhhh.ui.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_to_search.*

class ListFragment : Fragment(){
    companion object{
        fun newInstance(): ListFragment = ListFragment()
        lateinit var listViewModel: ListViewModel
    }

    lateinit var listAdapter: ListAdapterV3
    var mMealList: List<TMDBMealByIngredinet>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mMealList = mutableListOf()
        listAdapter =
            ListAdapterV3(mMealList as MutableList<TMDBMealByIngredinet>)
        home_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }
        listViewModel.item.observe(viewLifecycleOwner, Observer {
            listAdapter.setList(it)
        })
        listViewModel.getMealsByIngredient()

        listViewModel.shouldShowDetails.observe(viewLifecycleOwner, Observer {
            val searchFragment = HomeFragment()
            val fragmentTransaction: FragmentTransaction =
                activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, searchFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })
    }

}
