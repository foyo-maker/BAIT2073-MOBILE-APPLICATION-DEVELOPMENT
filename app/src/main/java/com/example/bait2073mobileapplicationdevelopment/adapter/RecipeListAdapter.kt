package com.example.bait2073mobileapplicationdevelopment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter (private val RecipeList:MutableList<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>(){

    fun setData(newData: List<Recipe>) {
        RecipeList.clear()
        RecipeList.addAll(newData)
        notifyDataSetChanged()
    }

    inner class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeListCard = itemView.findViewById<CardView>(R.id.recipe_layout)
        val recipeId = itemView.findViewById<TextView>(R.id.tv_recipeID)
        val recipeName = itemView.findViewById<TextView>(R.id.tv_recipeName)
        // Add other views as needed
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_recipe, parent, false)
        return RecipeListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        val currentRecipe = RecipeList[position]
        holder.recipeId.text= currentRecipe.id.toString()
        holder.recipeName.text = currentRecipe.recipe_name

        val recipeImageView = holder.itemView.findViewById<ImageView>(R.id.recipe_image)
        if (!currentRecipe.recipe_image.isNullOrBlank()) {
            Picasso.get().load(currentRecipe.recipe_image).fit().into(recipeImageView)

//            Picasso.get().load(currentCust.image).centerInside().into(custImageView)
        } else {
            // If no image URL is available,  set a placeholder image or handle this case as needed.\
            Log.e("noimage", "noimage")
            Picasso.get().load(R.drawable.diseases_recipe).into(recipeImageView)
        }
    }

    override fun getItemCount() = RecipeList.size
}