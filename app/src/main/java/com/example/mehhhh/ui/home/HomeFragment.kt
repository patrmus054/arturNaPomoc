package com.example.mehhhh.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.mehhhh.remote.Result
import com.example.mehhhh.remote.TMDBResult
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    companion object{
        fun newInstance(): HomeFragment = HomeFragment()
        lateinit var homeViewModel: HomeViewModel
    }

    lateinit var listAdapter: ListAdapter
    var mMealList: List<Result>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mMealList = mutableListOf()
        listAdapter =
            ListAdapter(mMealList as MutableList<TMDBResult>)
        home_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }
        homeViewModel.item.observe(viewLifecycleOwner, Observer {
            listAdapter.setList(it)
        })


        homeViewModel.shouldShowDetails.observe(viewLifecycleOwner, Observer {
            var intent = Intent(activity, MealDetailActivitty::class.java)
            startActivity(intent)
        })
        homeViewModel.getAllMeals()
        homeViewModel.shouldShowDetails.observe(viewLifecycleOwner, Observer {
            Log.w("nie wiem ", "czemu ")
            val homeFragment = HomeFragment()
            val fragmentTransaction: FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            Log.w("mejn", "activity")
        })


    }

}