import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bait2073mobileapplicationdevelopment.R
import com.example.bait2073mobileapplicationdevelopment.adapter.UserAdapter
import com.example.bait2073mobileapplicationdevelopment.entities.User
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlan
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.screens.fragment.MyTrainListFragment

class UserPlanListAdapter(private val context: Context, private val listener: UserPlanClickListener) :
    RecyclerView.Adapter<UserPlanListAdapter.UserPlanListViewHolder>() {

    private var ctx: Context? = null
    var UserPlanList = mutableListOf<UserPlanList>()
    var userPlanWorkoutfullList = mutableListOf<UserPlanList>()
    var UserPlan = mutableListOf<UserPlan>()
    var UserPlanfullList = mutableListOf<UserPlan>()


    fun setData(newData:  List<UserPlan>) {
        UserPlan.clear()
        UserPlan.addAll(newData)
        notifyDataSetChanged()
    }
    inner class UserPlanListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userPlan_layout = itemView.findViewById<CardView>(R.id.userPlan_layout)
        val planName = itemView.findViewById<TextView>(R.id.planName)
        val totalExcise = itemView.findViewById<TextView>(R.id.qtyPlantxt)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPlanListViewHolder {
        // Inflate your item layout and create a ViewHolder
        ctx = parent.context
        return UserPlanListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_userplanlist, parent,false)
        )
    }

    override fun getItemCount(): Int {

        return UserPlanList.size
    }

    override fun onBindViewHolder(holder: UserPlanListViewHolder, position: Int) {

        val currentUserPlanList = UserPlanList[position]
        val currentUserPlan = UserPlan[position]
        Log.e("userplan","$currentUserPlan")
        holder.planName.text = currentUserPlan.name
        holder.totalExcise.text = currentUserPlan.id.toString()


        holder.userPlan_layout.setOnClickListener{

            listener.onItemClicked(UserPlan[holder.adapterPosition],UserPlanList[holder.adapterPosition])

        }
        holder.userPlan_layout.setOnLongClickListener{

            listener.OnLongItemClicked(UserPlan[holder.adapterPosition],UserPlanList[holder.adapterPosition], holder.userPlan_layout)

            true
        }


    }
    fun updateUserPlanList(newList : List<UserPlan>){

        Log.e("userplanlist","$newList")
        UserPlanfullList.clear()
        UserPlanfullList.addAll(newList)
        UserPlan.clear()
        UserPlan.addAll(UserPlanfullList)
        Log.e("gg","$UserPlan")
        notifyDataSetChanged()
    }
    fun updateUserPlanWorkoutList(newList : List<UserPlanList>){
        userPlanWorkoutfullList.clear()
        userPlanWorkoutfullList.addAll(newList)
        UserPlanList.clear()
        UserPlanList.addAll(userPlanWorkoutfullList)
        notifyDataSetChanged()
    }


    interface UserPlanClickListener {
        fun onItemClicked(userPlan: UserPlan,userPlanList: UserPlanList)
        fun OnLongItemClicked(userPlan: UserPlan,userPlanList: UserPlanList,cardView: CardView)
    }
}

