package com.example.newappdebt.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class UserRepository(private val dao:UserDao){

    val userFlow:Flow<List<User>> = dao.getUsers()


    val userFlowFiltered:Flow<List<User>> = dao.getFiltredUsers()

    val userFlowFiltered2:Flow<List<User>> = dao.getFiltred()
    val userFlowIF:Flow<List<User>> = dao.getUsersWIthIf(isSwitched = false)

//    fun get_user_by_id(id: Int):User{
//        return dao.getUsersById(userId = id)
//    }



    suspend fun addUser(name:String, amount:Double,comment:String?, createdate:String,
                        isSwitch:Boolean, dateOfReturn:String){

       val newUser = User(
           name = name,
           amount = amount,
           dateTime = createdate,
           comment = comment,
           isSwitch = isSwitch,

           dateOfReturn = dateOfReturn
       )
        dao.insert(newUser)

    }

    suspend fun getUserById(id: Int): User {
        val userUnit = dao.getUsersById(userId = id)
        return   userUnit
    }

    suspend fun getHistoryByUser(id: Int): Debt_History {
        val debthistory = dao.getDebtHistoryByUser(userId = id)
        return debthistory
    }

    suspend fun deleteUser(user:User){
        dao.delete(user)
    }

    suspend fun deleteById(id:Int){
        dao.deleteById(userId = id )
    }

    suspend fun updateAmount(userId: Int, change:Double){
        val user = dao.getUsersById(userId = userId)

        if (user!=null){
            val updatedUser = user.copy(amount = user.amount + change)
            dao.updateUser(updatedUser)
        }
    }



    val debtFlow:Flow<List<Debt_History>> = dao.getDebtsHistory()





    suspend fun addDebt(dateEdit:String, amount:Double, isDebtReduce:Boolean,userId: Int){
        val newDebt = Debt_History(
            dateOfEdit = dateEdit,
            amount = amount,
            isDebtReduce = isDebtReduce,
            userId = userId
        )
        dao.Insert(newDebt)
    }


}

