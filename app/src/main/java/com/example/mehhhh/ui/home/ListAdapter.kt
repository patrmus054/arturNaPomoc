package com.example.mehhhh.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mehhhh.R
import com.example.mehhhh.databinding.HomeItemBinding
import com.example.mehhhh.remote.TMDBResult
import com.example.mehhhh.ui.home.HomeFragment.Companion.homeViewModel


class ListAdapter(private val list: MutableList<TMDBResult>)
    : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<HomeItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.home_item, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val meal: TMDBResult = list[position]
        holder.bind(meal, object : MealListener{
            override fun onMealSelected(meal: TMDBResult) {
                homeViewModel.setMeal(meal)
                homeViewModel.showDetail()
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

class BaseViewHolder(private val binding: HomeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var mTitleView: TextView? = null
    private var img: ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.tv_search_title)
        img = itemView.findViewById(R.id.img_home)
    }

    fun bind(mMeal: TMDBResult, mealListener: MealListener) {
        with(binding){
            meal = mMeal
            listener = mealListener
            mTitleView?.text = mMeal.strMeal

            Glide.with(binding.root)
                .load(mMeal.strMealThumb)
                .into(img!!)

            executePendingBindings()
        }

    }
}

interface MealListener{
    fun onMealSelected(meal: TMDBResult)
}
