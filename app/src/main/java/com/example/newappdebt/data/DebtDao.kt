package com.example.newappdebt.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DebtDao {



        @Query("SELECT * FROM debt_history")
        fun getDebtsHistory(): Flow<List<Debt_History>>

        @Query("SELECT * FROM debt_history WHERE id = :id")
        fun getDebtsById(id: Int): Flow<List<Debt_History>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun Insert(debt_history:Debt_History)



}