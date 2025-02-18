package com.example.newappdebt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//data class Debt(
//    val name:String,
//    val debt:Double,
//    val date:String
//)
//
//@Composable
//fun screen3() {
//    val debts by remember {
//        mutableStateOf(listOf(Debt("Стажер",1293.0000,"12.09.2024"),
//            Debt("Стажер",1293.0000,"12.09.2024"),
//            Debt("Стажер",1293.0000,"12.09.2024"),
//            Debt("Стажер",1293.0000,"12.09.2024"),Debt("Стажер",1293.0000,"12.09.2024")))
//    }
//
//    DebtList(debts)
//
//}
//
//
//@Composable
//fun screen4() {
//    val debts by remember {
//        mutableStateOf(listOf(Debt("Должник",1293.0000,"12.09.2024"),
//            Debt("Должник",1293.0000,"12.09.2024"),
//            Debt("Должник",1293.0000,"12.09.2024"),
//            Debt("Должник",1293.0000,"12.09.2024")))
//    }
//
//    DebtList(debts)
//
//}
//
//
//@Composable
//fun DebtList(debts:List<Debt>) {
//
//    if (debts.isEmpty()){
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.Blue),
//            contentAlignment = Alignment.Center){
//            Text(text = "Долгов нету")
//        }
//    }
//    else{
//        LazyColumn(modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(vertical = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)){
//            items(debts.size){
//                    debt -> DebtItem(debt = debts[debt])
//            }
//        }
//    }
//}
//
//@Composable
//fun DebtItem(debt: Any) {
//
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .padding(8.dp)
//        .background(Color.Transparent, shape = RoundedCornerShape(28.dp))
//    ) {
//
//        Column(modifier = Modifier
//            .background(Color(160, 163, 236))
//            .padding(6.dp)) {
//            Row() {
//                Text(text = debt.name, modifier = Modifier.weight(0.9f),
//                    fontWeight = FontWeight(500),
//                    color = Color(255, 255, 255),
//                    fontSize = 18.sp)
//
//                Text(text = debt.debt.toString(), fontWeight = FontWeight(700),
//                    color = Color(255, 255, 255),
//                    fontSize = 22.sp
//                )
//
//            }
//            Row() {
//                Spacer(modifier = Modifier.weight(0.9f))
//                Column() {
//
//                    Text(text = "Планируемая дата возврата", fontWeight = FontWeight(400),
//                        color = Color(229, 220, 252),
//                        fontSize = 12.sp)
//                }
//            }
//            Row() {
//                Spacer(modifier = Modifier.weight(0.9f))
//                Column() {
//                    Text(text = debt.date, fontWeight = FontWeight(500),
//                        color = Color(229, 220, 252),
//                        fontSize = 12.sp)
//                }
//            }
//        }
//
//    }
//
//}