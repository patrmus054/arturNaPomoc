package com.example.mehhhh.ui.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehhhh.R
import com.example.mehhhh.remote.TMDBIngredients
import com.example.mehhhh.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_ingredients.*


class IngredientFragment : Fragment() {

    companion object{
        fun newInstance(): IngredientFragment = IngredientFragment()
        lateinit var ingredientViewModel: IngredientViewModel

    }

    private lateinit var listAdapter: IngredientAdapter
    var mIngredientList: List<TMDBIngredients>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ingredientViewModel = ViewModelProviders.of(this).get(IngredientViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ingredients, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIngredientList = mutableListOf()
        listAdapter = IngredientAdapter(mIngredientList as MutableList<TMDBIngredients>)
        ingredients_recycler_view.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }
        ingredientViewModel.item.observe(viewLifecycleOwner, Observer {
            listAdapter.setList(it)
        })
        ingredientViewModel.getAllIngredients()
        ingredientViewModel.shouldShowList.observe(viewLifecycleOwner, Observer {
            val nextFrag = HomeFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_container, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit()
        })
    }

}

