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
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.squareup.picasso.Picasso

class UserAdapter (private val context : Context, val listener:UserClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

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
                .inflate(R.layout.recycleview_customer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    inner class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val user_layout = itemView.findViewById<CardView>(R.id.customer_layout)
        val id = itemView.findViewById<TextView>(R.id.tv_customerID)
        val userName = itemView.findViewById<TextView>(R.id.tv_customerName)
        val userEmail = itemView.findViewById<TextView>(R.id.tv_customerEmail)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {


        val currentUser = userList[position]
        holder.id.text = currentUser.id.toString()
        holder.userEmail.text = currentUser.email
        holder.userName.text = currentUser.name

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
//            Glide.with(ctx!!)
//                .load(R.drawable.img_person) // Use Glide for the placeholder image
//                .fitCenter() // Use fitCenter() for equivalent functionality
//                .into(custImageView)
            Picasso.get().load(R.drawable.img_person).into(custImageView)
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