package com.example.mehhhh.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.mehhhh.R
import kotlinx.android.synthetic.main.fragment_to_search.*


class ToSearchFragment: Fragment() {
    private lateinit var toSearchViewModel: ToSearchViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {toSearchViewModel = ViewModelProviders.of(this).get(ToSearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_to_search, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_search_meal_by_name.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", et_meal_name.text.toString())
            val searchFragment = SearchFragment()
            searchFragment.arguments = bundle
            val fragmentTransaction: FragmentTransaction =
                activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, searchFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }



}