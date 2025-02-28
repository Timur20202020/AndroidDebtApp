package com.example.newappdebt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newappdebt.data.User
import com.example.newappdebt.mvvm.UserViewModel


@Composable
fun  Main_screen(viewModel: UserViewModel, navController: NavController){

      val users by viewModel.users.collectAsState()
      val usersFiltered by viewModel.usersFiltered.collectAsState()
      val usersFiltered2 by viewModel.usersFiltered2.collectAsState()

      val usersFilteredIf by viewModel.usersFilteredIf.collectAsState()

//      var isIDebetor by remember { mutableStateOf(usersFiltered2) }


      var selectedList by rememberSaveable { mutableStateOf(1) }

      val tabcolor by rememberSaveable { mutableStateOf(Color(160, 163, 236)) }


    val currentList = when (selectedList) {
        1 -> usersFiltered
        2 -> usersFiltered2
        3 -> usersFilteredIf
        else -> users
    }


//    var sum:Int  = 0

//          for (i in users){
//              sum += i.amount.toInt()
//      }

    fun sum(list:List<User>):Int{
        var sum:Int  = 0
        for (i in list){
            sum += i.amount.toInt()
        }
        return sum
    }




//    Комментарии к коду
//    Второй комментарий
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("create") },
                containerColor = Color(47, 49, 109),
                contentColor = Color.White,
                shape = CircleShape
            ) {

                Icon(Icons.Default.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(101, 105, 212)
                        )
            ) {
                Column {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(47, 49, 109))
                        .padding(top = 28.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        val image: Painter = painterResource(id = R.drawable.card)
                        val cardicon = Image(
                            painter = image,
                            contentDescription = "Icon Description"
                        )
                        Spacer(Modifier.height(24.dp))
//                        Text(text = "  ${sum.toString()}₽",fontWeight = FontWeight(700),
//                            color = Color(229, 220, 252),
//                            fontSize = 26.sp)
//                    }
                    Text(text = if (selectedList==1){"  ${sum(usersFiltered).toString()}₽"}
                         else{"  ${sum(usersFiltered2).toString()}₽"},

                        fontWeight = FontWeight(700),
                        color = Color(229, 220, 252),
                        fontSize = 26.sp)
                }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(47, 49, 109))
                            .padding(top = 28.dp, end = 48.dp, start = 48.dp, bottom = 8.dp)
                    ) {

                        Text(text = "Я ДОЛЖЕН",
                            fontWeight = FontWeight(500),
//                            color = Color(229, 220, 252),
                            color = if (selectedList==1){tabcolor}else{Color(229, 220, 252)},
                            fontSize = 16.sp,
                            modifier = Modifier.weight(0.8f).
                            clickable {
//                                isIDebetor = usersFiltered
                                  selectedList = 1
                            }
                        )

                        Spacer(modifier = Modifier.width(28.dp))
                        Text(text = "МНЕ ДОЛЖНЫ", fontWeight = FontWeight(500),
//                            color = Color(229, 220, 252),
                            color = if (selectedList==2){tabcolor}else{Color(229, 220, 252)},
                            fontSize = 16.sp,
                            modifier = Modifier.
                            clickable {
//                                isIDebetor = usersFiltered2
                                  selectedList = 2
                            })
                    }
                   Row(modifier = Modifier.fillMaxWidth()) {
                       Row(Modifier.background(
                           if (selectedList==1){Color(229, 220, 252)}
                           else{Color.Transparent}
                       ).weight(0.4f)) {
                           Spacer(Modifier.height(2.dp))
                       }
//                       Row(Modifier.weight(0.2f)) {
//                           Spacer(Modifier.height(2.dp))
//                       }
                       Row(Modifier.background(
                           if (selectedList==2){Color(229, 220, 252)}
                           else{Color.Transparent}
                       ).weight(0.4f)) {
                           Spacer(Modifier.height(2.dp))
                       }
                   }

                    DebtList(currentList, navController = navController)
                }
            }


        }
    )

}







@Composable
fun DebtList(debts:List<User>,navController: NavController) {

    if (debts.isEmpty()){
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(text = "Долгов нету")
        }
    }
    else{
        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)){
            items(debts.size){
                    debt -> DebtItem(debt = debts[debt], navController = navController)
            }
        }
    }
}

@Composable
fun DebtItem(debt: User, navController: NavController) {

    Card(elevation = CardDefaults.cardElevation(defaultElevation = 14.dp),
        modifier = Modifier

//        .shadow(
//        elevation = 6.dp,
//        shape = RoundedCornerShape(10.dp)
//
//    )
        .fillMaxWidth()
        .padding(8.dp)

        .background(Color.Transparent, shape = RoundedCornerShape(28.dp))
        .clickable {
            navController.navigate("edit/${debt.id}")
        }
    ) {

        Column(modifier = Modifier.
             background(Color(160, 163, 236))
            .padding(6.dp)) {
            Row() {
                Text(text = "${debt.name}", modifier = Modifier.weight(0.9f),
                    fontWeight = FontWeight(500),
                    color = Color(255, 255, 255),
                    fontSize = 18.sp)

                Text(text ="${debt.amount.toInt()} ₽" , fontWeight = FontWeight(700),
                    color = Color(255, 255, 255),
                    fontSize = 22.sp
                )



            }
            Row() {
                Spacer(modifier = Modifier.weight(0.9f))
                Column() {

                    Text(text = "Планируемая дата возврата", fontWeight = FontWeight(400),
                        color = Color(229, 220, 252),
                        fontSize = 12.sp,
                        modifier = Modifier.clickable {
                           navController.navigate("edit/${debt.id}")
                        })
                }
            }
            Row() {

                Spacer(modifier = Modifier.weight(0.9f))
                Column() {
                    Text(text = debt.dateOfReturn, fontWeight = FontWeight(500),
                        color = Color(229, 220, 252),
                        fontSize = 12.sp)
//                    Text(text = debt.isSwitch.toString(), fontWeight = FontWeight(500),
//                        color = Color(229, 220, 252),
//                        fontSize = 12.sp)
                }
            }
        }

    }

}