package com.example.mehhhh.ui.ingredients

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mehhhh.R
import com.example.mehhhh.databinding.HomeItem3Binding
import com.example.mehhhh.databinding.HomeItemBinding
import com.example.mehhhh.remote.TMDBMealByIngredinet
import com.example.mehhhh.remote.TMDBResult
import com.example.mehhhh.ui.home.HomeFragment.Companion.homeViewModel
import com.example.mehhhh.ui.ingredients.IngredientFragment.Companion.ingredientViewModel
import com.example.mehhhh.ui.ingredients.ListFragment.Companion.listViewModel


class ListAdapterV3(private val list: MutableList<TMDBMealByIngredinet>)
    : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<HomeItem3Binding>(
            LayoutInflater.from(parent.context),
            R.layout.home_item_3, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val meal: TMDBMealByIngredinet = list[position]
        holder.bind(meal, object : MealListener{
            override fun onIngredientSelected(ingredinet: TMDBMealByIngredinet) {
                Log.w("V3Adapter", "witam")
                homeViewModel.getMealsByIngredientId(ingredient = ingredinet.name.toString())
                homeViewModel.showList()
            }
        })
    }

    override fun getItemCount(): Int = list.size

    fun setList(newlist: List<TMDBMealByIngredinet>){
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }

}

class BaseViewHolder(private val binding: HomeItem3Binding) :
    RecyclerView.ViewHolder(binding.root) {
    private var mTitleView: TextView? = null
    private var img: ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.tv_list_search_title)
        img = itemView.findViewById(R.id.img_list_search)
    }

    fun bind(iIngredinet: TMDBMealByIngredinet, mealListener: MealListener) {
        with(binding){
            ingredient = iIngredinet
            listener = mealListener
            mTitleView?.text = iIngredinet.name

            Glide.with(binding.root)
                .load(iIngredinet.img)
                .into(img!!)

            executePendingBindings()
        }

    }
}

interface MealListener{
    fun onIngredientSelected(meal: TMDBMealByIngredinet)
}
