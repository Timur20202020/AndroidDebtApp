package com.example.newappdebt.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
//@Serializable
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name:String,
    var amount: Double,
    val dateTime:String,
    val dateOfReturn:String,
    val comment:String?,
    val isSwitch:Boolean
)

