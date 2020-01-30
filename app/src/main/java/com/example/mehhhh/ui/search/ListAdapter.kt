package com.example.mehhhh.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mehhhh.R
import com.example.mehhhh.remote.Result
import com.example.mehhhh.remote.TMDBResult

class ListAdapter(private val list: MutableList<TMDBResult>)
    : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val meal: TMDBResult = list[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int = list.size

    fun setList(newlist: List<TMDBResult>){
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }

}

class BaseViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.search_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mIngredientView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.tv_search_title)
        mIngredientView = itemView.findViewById(R.id.tv_search_not_possessed_ingredients)
    }

    fun bind(meal: TMDBResult) {
        mTitleView?.text = meal.strMeal
        mIngredientView?.text = meal.strIngredient1
    }
}