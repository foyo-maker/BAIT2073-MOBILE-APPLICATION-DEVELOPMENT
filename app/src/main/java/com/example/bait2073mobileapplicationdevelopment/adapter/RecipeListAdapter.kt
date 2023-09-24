package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter (private val context : Context, val listener:RecipeClickListener) : RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>(){

    private var ctx: Context? = null
    var fullList = mutableListOf<Recipe>()
    var recipeList = mutableListOf<Recipe>()

    fun setData(arrData: List<Recipe>) {
        recipeList = arrData as ArrayList<Recipe>
    }

    fun updateList(newList : List<Recipe>){
        fullList.clear()
        fullList.addAll(newList)
        recipeList.clear()
        recipeList.addAll(fullList)
        notifyDataSetChanged()
    }

    inner class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeListCard = itemView.findViewById<CardView>(R.id.recipe_layout)
        val recipeId = itemView.findViewById<TextView>(R.id.tv_recipeID)
        val recipeName = itemView.findViewById<TextView>(R.id.tv_recipeName)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        ctx = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_recipe, parent, false)
        return RecipeListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.recipeId.text = currentRecipe.id.toString()
        holder.recipeName.text = currentRecipe.recipe_name

        val recipeImageView = holder.itemView.findViewById<ImageView>(R.id.recipe_image)
        if (!currentRecipe.recipe_image.isNullOrBlank()) {
            Glide.with(ctx!!)
                .load(currentRecipe.recipe_image)
                .fitCenter()
                .into(recipeImageView)

        } else {

            Log.e("noimage", "noimage")
            Picasso.get().load(R.drawable.diseases_recipe).into(recipeImageView)
        }

        holder.recipeListCard.setOnClickListener {

            listener.onItemClicked(recipeList[holder.adapterPosition])

        }
        holder.recipeListCard.setOnLongClickListener {

            listener.onLongItemClicked(recipeList[holder.adapterPosition], holder.recipeListCard)
            true
        }
    }
    override fun getItemCount(): Int {
        return recipeList.size
    }

        fun filterList(search : String){
            recipeList.clear()

            for(item in fullList){

                if( item.recipe_name?.lowercase()?.contains(search
                        .lowercase())==true){
                    recipeList.add(item)

                }
            }
            Log.i("recipeList", "$recipeList")

            notifyDataSetChanged()
        }
    interface RecipeClickListener{
        fun onItemClicked(recipe: Recipe)
        fun onLongItemClicked(recipe:Recipe,cardView:CardView)
    }


}