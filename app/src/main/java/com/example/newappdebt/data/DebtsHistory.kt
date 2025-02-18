package com.example.newappdebt.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "debt_history"
//    , foreignKeys = [ForeignKey(
//    entity = User::class,
//    parentColumns = arrayOf("id"),
//    childColumns = arrayOf("userId"),
//    onDelete = ForeignKey.CASCADE,
//    onUpdate = ForeignKey.CASCADE
//)]
)
data class Debt_History(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateOfEdit:String,
    val amount: Double,
    var isDebtReduce:Boolean,
    // внешний ключ к таблице User
//    @Embedded
    val userId:Int

)
