package com.example.newappdebt.di

import android.content.Context
import androidx.room.Room
import com.example.newappdebt.data.AppDatabase
import com.example.newappdebt.data.DebtRepository
import com.example.newappdebt.data.UserDao
import com.example.newappdebt.data.UserRepository


object AppModule{


    fun provideDatabase(context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"app_db").build()
    }

    fun provideRepository(db:AppDatabase):UserRepository{
        return UserRepository(db.userDao())
    }
    fun provideDebtRepository(db:AppDatabase):DebtRepository{
        return DebtRepository(db.debtDao())
    }

}