package com.example.newappdebt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.newappdebt.data.User
import com.example.newappdebt.mvvm.DebtViewModel
import com.example.newappdebt.mvvm.UserViewModel
import kotlinx.serialization.json.Json

@Composable
fun editDebtUser(
    viewModel: UserViewModel,
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    debtViewModel: DebtViewModel
){




     val users by viewModel.users.collectAsState()
    val debthistory by debtViewModel.debtHistory.collectAsState()

     val debt = backStackEntry.arguments?.getString("debt") ?: ""

    // десериализация
//     val user = Json.decodeFromString<User>(debt ?: "")



//    val debthistory by viewModel.debtHistory.collectAsState()


    var textinput by remember { mutableStateOf("") }


    EditSreenExperimental(viewModel,
        navController,
        backStackEntry,
        debtViewModel)
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(
//            Color(
//                101,
//                105, 212
//            )
//        )
//        .padding(24.dp)
////        ,
////        contentAlignment = Alignment.Center
//        ) {
//
//
//
//
//
//            LazyColumn {
//                items(debthistory.size){
//                    debthistoryunit -> Text("${debthistory[debthistoryunit]}")
//                }
//            }
//
//            Row(modifier = Modifier.fillMaxWidth().padding(16.dp))
//            {
//
//                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)){
//                    TextField(value = textinput,onValueChange = {textinput=it})
//                    Button(onClick = {
//                        debtViewModel.addDebt(
//                            dateEdit = "",
//                            amount = textinput,
//                            isDebtReduce = true
//                        )
//                    }
//                    ) { Text("+") }
//                }
//                IconButton(onClick = {
//                    navController.popBackStack()
//                }, modifier = Modifier.weight(0.8f)
//                    ) {
//                    Icon(
//                        imageVector = Icons.Default.KeyboardArrowDown,
//                        contentDescription = "Удалить",
//                         tint = Color.White
//                        )
//                }
//
//                IconButton(onClick = {
//                       viewModel.deleteUserByID(user.id)
//                     }) {
//                    Icon(
//                        imageVector = Icons.Default.Delete,
//                        contentDescription = "Удалить",
//                        tint = Color.White
//                    )
//                }
//            }
//
//            Text(user.name)
//            user.comment?.let { Text(it) }
//            Text("Дата создания ${user.dateTime}")
//            Text("Дата погашения ${user.dateOfReturn}")
//
//
//            Button(onClick = {
//
//
//                viewModel.deleteUserByID(user.id)
//                // Вызов функции удаления долга
//                navController.popBackStack()
//            }) {
//                Text("Удалить долг c id ${user.id} ")
//            }
//        }

    }
