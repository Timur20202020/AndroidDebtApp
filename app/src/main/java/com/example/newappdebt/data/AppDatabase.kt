package com.example.newappdebt.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [User::class,Debt_History::class], version = 1, exportSchema = false )
abstract class AppDatabase:RoomDatabase(){

    abstract fun userDao():UserDao
    abstract fun debtDao():DebtDao
}

