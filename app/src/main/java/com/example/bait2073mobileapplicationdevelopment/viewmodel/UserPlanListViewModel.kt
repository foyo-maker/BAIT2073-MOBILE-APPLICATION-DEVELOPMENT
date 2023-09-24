import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bait2073mobileapplicationdevelopment.database.HealthyLifeDatabase
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlan
import com.example.bait2073mobileapplicationdevelopment.entities.UserPlanList
import com.example.bait2073mobileapplicationdevelopment.repository.PersonalizedWorkoutRepository
import com.example.bait2073mobileapplicationdevelopment.repository.UserPlanListRepository
import com.example.bait2073mobileapplicationdevelopment.repository.UserPlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPlanListModel(application: Application) : AndroidViewModel(application) {
    private val allUserPlanRepository: UserPlanListRepository
    private val userPlanRepository: UserPlanRepository

    private val allUserPlanList: LiveData<List<UserPlanList>>
    private val allUserPlans: LiveData<List<UserPlan>>

    init {
        val UserPlanListdao = HealthyLifeDatabase.getDatabase(application).userPlanListDao()
        val UserPlandao = HealthyLifeDatabase.getDatabase(application).userPlanDao()
        allUserPlanRepository = UserPlanListRepository(UserPlanListdao)
        allUserPlanList = allUserPlanRepository.allUserPlanList
        userPlanRepository = UserPlanRepository(UserPlandao)
        allUserPlans = userPlanRepository.allUserPlan
    }

    // Function to insert a user plan
    fun insertUserPlanList(userPlanList: UserPlanList) = viewModelScope.launch(Dispatchers.IO) {
        allUserPlanRepository.insertWorkout(userPlanList)
    }
    fun insertUserPlan(userPlan: UserPlan) = viewModelScope.launch(Dispatchers.IO) {
        userPlanRepository.insertUserPlan(userPlan)
    }
    // Function to get user plan workout by ID
    fun getUserPlanListById(userPlanId: Int): LiveData<List<UserPlanList>> {
        return allUserPlanRepository.getAllWorkoutsByUserPlanId(userPlanId)
    }
    // Function to get user plan by User ID
    suspend fun getUserPlanById(userId: Int): LiveData<List<UserPlan>> {
        return userPlanRepository.getUserPlansByUserId(userId)
    }
    // Define a function to change the name of a user plan by ID
    fun updateUserPlanName(userPlan: UserPlan) = viewModelScope.launch(Dispatchers.IO) {
        userPlanRepository.updateUserPlan(userPlan)
    }
    // Define a function to delete a user plan by ID
    fun deleteUserPlan(userPlan: UserPlan) = viewModelScope.launch(Dispatchers.IO) {
        userPlanRepository.deleteUserPlan(userPlan)
    }
    // Function to delete workouts by userPlanListId
    fun deleteWorkoutsByUserPlanList(userPlanList: List<UserPlanList>) {
        viewModelScope.launch(Dispatchers.IO) {
            allUserPlanRepository.deleteWorkoutsByUserPlan(userPlanList)
        }
        fun clearWorkout() = viewModelScope.launch(Dispatchers.IO) {
            allUserPlanRepository.clearWorkout()
        }
    }
    fun clearWorkout() = viewModelScope.launch(Dispatchers.IO) {
        allUserPlanRepository.clearWorkout()
    }
}
