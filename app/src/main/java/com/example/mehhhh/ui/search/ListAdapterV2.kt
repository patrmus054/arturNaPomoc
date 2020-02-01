package com.example.mehhhh.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mehhhh.R
import com.example.mehhhh.databinding.HomeItem2Binding
import com.example.mehhhh.remote.TMDBResult


class ListAdapterV2(private val list: MutableList<TMDBResult>)
    : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<HomeItem2Binding>(
            LayoutInflater.from(parent.context),
            R.layout.home_item_2, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val meal: TMDBResult = list[position]
        holder.bind(meal, object : MealListener{
            override fun onMealSelected(meal: TMDBResult) {
                SearchFragment.searchViewModel.setMeal(meal)
                SearchFragment.searchViewModel.showDetail()
            }
        })
    }

    override fun getItemCount(): Int = list.size

    fun setList(newlist: List<TMDBResult>){
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }

}

class BaseViewHolder(private val binding: HomeItem2Binding) :
    RecyclerView.ViewHolder(binding.root) {
    private var mTitleView: TextView? = null
    private var img: ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.tv_search_title)
        img = itemView.findViewById(R.id.img_search)
    }

    fun bind(mMeal: TMDBResult, mealListener: MealListener) {
        with(binding){
            meal = mMeal
            listener = mealListener
            mTitleView?.text = mMeal.strMeal

//            Glide.with(binding.root)
//                .load(mMeal.strMealThumb)
//                .into(img!!)
            executePendingBindings()
        }

    }
}

interface MealListener{
    fun onMealSelected(meal: TMDBResult)
}
