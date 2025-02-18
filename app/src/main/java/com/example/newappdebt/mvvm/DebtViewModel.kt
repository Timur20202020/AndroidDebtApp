package com.example.newappdebt.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newappdebt.data.DebtRepository
import com.example.newappdebt.data.Debt_History
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DebtViewModel(private val repository: DebtRepository):ViewModel() {


    val debtHistory = repository.debtFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), emptyList())


    fun addDebt(dateEdit:String,amount:Double,isDebtReduce:Boolean, id:Int){
        viewModelScope.launch {
            repository.addDebt(
                dateEdit = dateEdit,
                amount = amount,
                isDebtReduce = isDebtReduce,
                id = id
            )
        }
    }
    fun getDebtsById(id: Int): Flow<List<Debt_History>> {
        return repository.getDebtsById(id)
    }
}