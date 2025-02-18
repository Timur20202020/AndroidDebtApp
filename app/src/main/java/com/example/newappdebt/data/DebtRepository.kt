package com.example.newappdebt.data

import kotlinx.coroutines.flow.Flow

class DebtRepository(private val dao:DebtDao) {

    val debtFlow:Flow<List<Debt_History>> = dao.getDebtsHistory()

    suspend fun addDebt(dateEdit:String, amount:Double, isDebtReduce:Boolean,id:Int){
        val newDebt = Debt_History(
            dateOfEdit = dateEdit,
            amount = amount,
            isDebtReduce = isDebtReduce,
            userId = id
        )
      dao.Insert(newDebt)
    }

    // Получаем историю долгов по конкретному id
    fun getDebtsById(id: Int): Flow<List<Debt_History>> {
        return dao.getDebtsById(id)
    }

}