package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.squareup.picasso.Picasso

class UserRatingAdapter (private val context : Context, val listener:UserClickListener): RecyclerView.Adapter<UserRatingAdapter.UserViewHolder>() {


    private var ctx: Context? = null
    var userList = mutableListOf<User>()
    var fullList = mutableListOf<User>()


    fun setData(arrData: List<User>) {
        userList = arrData as ArrayList<User>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        ctx = parent.context
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_rating, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    inner class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val user_layout = itemView.findViewById<CardView>(R.id.rating_layout)
        val id = itemView.findViewById<TextView>(R.id.tv_customerID)
        val userName = itemView.findViewById<TextView>(R.id.tv_customerName)
        val userEmail = itemView.findViewById<TextView>(R.id.tv_customerEmail)



    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {


        val currentUser = userList[position]
        holder.id.text = currentUser.id.toString()
        holder.userEmail.text = currentUser.email
        holder.userName.text = currentUser.name



        // Set star icons based on the user's rating
        val star1 = holder.itemView.findViewById<ImageView>(R.id.star1)
        val star2 = holder.itemView.findViewById<ImageView>(R.id.star2)
        val star3 = holder.itemView.findViewById<ImageView>(R.id.star3)
        val star4 = holder.itemView.findViewById<ImageView>(R.id.star4)
        val star5 = holder.itemView.findViewById<ImageView>(R.id.star5)

        val rating = currentUser.rating?.toFloat() ?: 0.0f

        Log.d("Rating", "Rating: $rating")

        star1.visibility = if (rating >= 1.0f) {
            Log.d("StarVisibility", "Star 1 is visible")
            View.VISIBLE
        } else {
            Log.d("StarVisibility", "Star 1 is gone")
            View.GONE
        }

        star1.visibility = if (rating >= 0.5f) View.VISIBLE else View.GONE
        star2.visibility = if (rating >= 1.5f) View.VISIBLE else View.GONE
        star3.visibility = if (rating >= 2.5f) View.VISIBLE else View.GONE
        star4.visibility = if (rating >= 3.5f) View.VISIBLE else View.GONE
        star5.visibility = if (rating >= 4.5f) View.VISIBLE else View.GONE


        when (rating) {
            in 0.1f..0.5f -> star1.setImageResource(R.drawable.halfstar)
            in 0.6f..1.0f -> star1.setImageResource(R.drawable.fullstar)
            in 1.1f..1.5f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.halfstar)
            }

            in 1.6f..2.0f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
            }

            in 2.1f..2.5f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
                star3.setImageResource(R.drawable.halfstar)
            }

            in 2.6f..3.0f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
                star3.setImageResource(R.drawable.fullstar)
            }

            in 3.1f..3.5f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
                star3.setImageResource(R.drawable.fullstar)
                star4.setImageResource(R.drawable.halfstar)
            }

            in 3.6f..4.0f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
                star3.setImageResource(R.drawable.fullstar)
                star4.setImageResource(R.drawable.fullstar)
            }

            in 4.1f..4.5f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
                star3.setImageResource(R.drawable.fullstar)
                star4.setImageResource(R.drawable.fullstar)
                star5.setImageResource(R.drawable.halfstar)
            }

            in 4.6f..5.0f -> {
                star1.setImageResource(R.drawable.fullstar)
                star2.setImageResource(R.drawable.fullstar)
                star3.setImageResource(R.drawable.fullstar)
                star4.setImageResource(R.drawable.fullstar)
                star5.setImageResource(R.drawable.fullstar)
            }
        }
        val custImageView = holder.itemView.findViewById<ImageView>(R.id.customer_image)
        if (!currentUser.image.isNullOrBlank()) {


//            Picasso.get().load(currentUser.image).fit().into(custImageView)
            Glide.with(ctx!!)
                .load(currentUser.image)
                .fitCenter() // Use fitCenter() for equivalent functionality
                .into(custImageView)

        } else {
            // If no image URL is available,  set a placeholder image or handle this case as needed.\
            Log.e("noimage", "noimage")
            Glide.with(ctx!!)
                .load(R.drawable.img_person) // Use Glide for the placeholder image
                .fitCenter() // Use fitCenter() for equivalent functionality
                .into(custImageView)
//            Picasso.get().load(R.drawable.img_person).into(custImageView)
        }
        //image
//        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.itemView.img_dish)

        holder.user_layout.setOnClickListener{

            listener.onItemClicked(userList[holder.adapterPosition])

        }
        holder.user_layout.setOnLongClickListener{

            listener.onLongItemClicked(userList[holder.adapterPosition],holder.user_layout)

            true
        }
    }

    fun updateList(newList : List<User>){
        fullList.clear()
        fullList.addAll(newList)
        userList.clear()
        userList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filerList(search : String){
        userList.clear()

        for(item in fullList){

            if(item.email?.lowercase()?.contains(search.lowercase())==true || item.name?.lowercase()?.contains(search
                    .lowercase())==true){
                userList.add(item)

            }
        }
        Log.i("userlist", "$userList")

        notifyDataSetChanged()
    }



    interface UserClickListener{
        fun onItemClicked(user:User)
        fun onLongItemClicked(user:User,cardView:CardView)
    }

}