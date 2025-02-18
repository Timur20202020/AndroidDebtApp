package com.example.newappdebt.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappdebt.data.Debt_History
import com.example.newappdebt.data.User
import com.example.newappdebt.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class UserViewModel(private  val repository: UserRepository) : ViewModel() {



    val users = repository.userFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())

    val usersFiltered = repository.userFlowFiltered.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())
    val usersFiltered2 = repository.userFlowFiltered2.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())

    val usersFilteredIf = repository.userFlowIF.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())



    private val _userLiveData = MutableStateFlow<User?>(null)
    val userLiveData: MutableStateFlow<User?>  = _userLiveData


    private val _debtFlow = MutableStateFlow<List<Debt_History>>(emptyList())
    val debtFlowHistory: StateFlow<List<Debt_History>> =_debtFlow

//    fun get_user_by_id(id: Int):User {
//        return repository.get_user_by_id(id=id)
//    }

//    val debtHistory = debtrep.debtFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())

//    fun addDebt(dateEdit:String,amount:String,isDebtReduce:Boolean){
//        viewModelScope.launch {
//            debtrep.addDebt(
//                dateEdit = dateEdit,
//                amount = amount,
//                isDebtReduce = isDebtReduce
//            )
//        }
//    }

    fun addUSer(name:String, amount: Double, comment:String?, createdate: String,
                isSwitch:Boolean,
                dateOfReturn:String){
        viewModelScope.launch {
            repository.addUser(
                name,
                amount,
                comment,
                createdate,
                isSwitch,
                dateOfReturn
            )
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun getUserById(id: Int){
        viewModelScope.launch {
            try {
                val user = repository.getUserById(id)  // Убедитесь, что getUserById в repository - suspend функция
                _userLiveData.value = user
            } catch (e: Exception) {
                // Обработайте ошибку, если нужно
                Log.e("Error", "Failed to get user by ID", e)
            }
        }
    }


    fun getDebtByUser(id: Int){
        viewModelScope.launch {
            try {
                val debtHistory =   repository.getHistoryByUser(id)  // Убедитесь, что getUserById в repository - suspend функция
                _debtFlow.value  = listOf(debtHistory)
            } catch (e: Exception) {
                // Обработайте ошибку, если нужно
                Log.e("Error", "Failed to get user by ID", e)
            }

        }
    }

    fun updateUser(userId: Int, change:Double){
        viewModelScope.launch {
            repository.updateAmount(userId=userId,change=change)
        }
    }

    fun deleteUserByID(id:Int){
        viewModelScope.launch {
            repository.deleteById(id = id)
        }
    }

    val debtHistory = repository.debtFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())




    fun addDebt(dateEdit:String,amount:Double,isDebtReduce:Boolean,userId: Int){
        viewModelScope.launch {
            repository.addDebt(
                dateEdit = dateEdit,
                amount = amount,
                isDebtReduce = isDebtReduce,
                userId = userId
            )
        }
    }


}


