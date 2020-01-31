package com.example.mehhhh.ui.ingredients

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mehhhh.R
import com.example.mehhhh.databinding.HomeItemBinding
import com.example.mehhhh.databinding.IngredientItemBinding
import com.example.mehhhh.remote.TMDBIngredients
import com.example.mehhhh.remote.TMDBResult
import com.example.mehhhh.ui.home.HomeFragment.Companion.homeViewModel
import com.example.mehhhh.ui.ingredients.IngredientFragment.Companion.ingredientViewModel

class IngredientAdapter(private  val list: MutableList<TMDBIngredients>)
    : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val binding = DataBindingUtil.inflate<IngredientItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.ingredient_item, parent, false
        )
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient: TMDBIngredients = list[position]
        holder.bind(ingredient, object : IngredientListener{
            override fun onIngredientSelected(ingredient: TMDBIngredients) {
                Log.w("Jestem", ingredient.name)
                ingredientViewModel.setIngredient(ingredient)
                ingredientViewModel.showList()
            }
        })
    }

    override fun getItemCount(): Int = list.size

    fun setList(newlist: List<TMDBIngredients>) {
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }

    class IngredientViewHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var mTitle: TextView? = null

        init {
            mTitle = itemView.findViewById(R.id.tv_ingredient)
        }

        fun bind(ingredient: TMDBIngredients, ingredientListener: IngredientListener) {
            with(binding){
                mTitle?.text = ingredient.name
            }

        }

    }


}

interface IngredientListener{
    fun onIngredientSelected(ingredient: TMDBIngredients)
}
