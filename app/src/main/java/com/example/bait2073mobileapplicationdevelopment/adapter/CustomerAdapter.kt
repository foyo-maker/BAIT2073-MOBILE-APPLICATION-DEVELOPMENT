package com.example.bait2073mobileapplicationdevelopment.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.squareup.picasso.Picasso

class CustomerAdapter (private val context : Context, val listener: CustomerClickListener): RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {


    private var ctx: Context? = null
    var userList = mutableListOf<User>()
    var fullList = mutableListOf<User>()


    fun setData(arrData: List<User>) {
        userList = arrData as ArrayList<User>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        ctx = parent.context
        return CustomerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_customer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    inner class CustomerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val customer_layout = itemView.findViewById<CardView>(R.id.customer_layout)
        val id = itemView.findViewById<TextView>(R.id.tv_customerID)
        val custName = itemView.findViewById<TextView>(R.id.tv_customerName)
        val custEmail = itemView.findViewById<TextView>(R.id.tv_customerEmail)



    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {


        val currentCust = userList[position]
        holder.id.text = currentCust.id.toString()
        holder.custEmail.text = currentCust.email
        holder.custName.text = currentCust.name


        val custImageView = holder.itemView.findViewById<ImageView>(R.id.customer_image)
        if (!currentCust.image.isNullOrBlank()) {
            Picasso.get().load(currentCust.image).fit().into(custImageView)

//            Picasso.get().load(currentCust.image).centerInside().into(custImageView)
        } else {
            // If no image URL is available,  set a placeholder image or handle this case as needed.\
            Log.e("noimage", "noimage")
            Picasso.get().load(R.drawable.img_person).into(custImageView)
        }
        //image
//        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.itemView.img_dish)

        holder.customer_layout.setOnClickListener{
            Log.i("foyo", "foyo")
            listener.onItemClicked(userList[holder.adapterPosition])
            Log.d("foyoyyyyy", userList[holder.adapterPosition].toString())
        }
        holder.customer_layout.setOnLongClickListener{
            Log.i("wacao", "wwacao")
            listener.onLongItemClicked(userList[holder.adapterPosition],holder.customer_layout)
            Log.i("wacao", holder.customer_layout.toString())
            true
        }
    }

    fun updateList(newList : List<User>){
        fullList.clear()
        Log.i("foyo", "$fullList")
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

    interface CustomerClickListener{
        fun onItemClicked(user:User)
        fun onLongItemClicked(user:User,cardView:CardView)
    }

}