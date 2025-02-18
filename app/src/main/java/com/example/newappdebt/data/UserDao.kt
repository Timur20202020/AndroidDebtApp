package com.example.newappdebt.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUsersById(userId: Int):User



    @Query("SELECT * FROM users WHERE isSwitch = 1")
    fun getFiltredUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE isSwitch = 0")
    fun getFiltred(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE isSwitch = :isSwitched")
    fun getUsersWIthIf(isSwitched:Boolean): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Int)



    @Query("SELECT * FROM debt_history")
    fun getDebtsHistory(): Flow<List<Debt_History>>

    @Query("SELECT * FROM debt_history  WHERE userId = :userId")
    fun getDebtHistoryByUser(userId: Int):Flow<List<Debt_History>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(debt_history:Debt_History)

    @Update
    suspend fun update(debt: Debt_History)
}

