package com.example.newappdebt.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newappdebt.DebtScreen
import com.example.newappdebt.Main_screen
import com.example.newappdebt.editDebtUser
import com.example.newappdebt.mvvm.DebtViewModel
import com.example.newappdebt.mvvm.UserViewModel
import java.time.LocalDate


//@Composable
//fun listViewModel(viewModel: UserViewModel,vm:DebtViewModel){
//
//
//    val navController = rememberNavController()
//
//
//
//    NavHost(navController,
//        startDestination = "main3"
//    ){
//
//        composable("create"){ createDebtCreate(viewModel, navController) }
//        composable("edit/{debt}"){ backStackEntry -> editDebt(viewModel, navController,backStackEntry) }
//
//
//
//    }
//
//}


@Composable
fun NavGraf(viewModel: UserViewModel, debtViewModel: DebtViewModel){


//    val context = LocalContext.current
//    val db = AppModule.provideDatabase(context)
//    val repository = AppModule.provideRepository(db)
//
//
//    val viewModel = UserViewModel(repository)
    val navController = rememberNavController()



    NavHost(navController,
          startDestination = "main3"
        ){
//        composable("main2"){ userScreen2(viewModel, navController) }
        composable("main3"){ Screen3(viewModel, navController) }
        composable("create"){ createDebtCreate(viewModel, navController) }
        composable("edit/{id}"){ backStackEntry -> editDebt(viewModel,debtViewModel, navController,backStackEntry) }
        composable("changeAmount/{id}/{negativesign}"){backStackEntry -> changeAmountscreen(viewModel,debtViewModel, navController,backStackEntry,)}


    }
}

@Composable
fun changeAmountscreen(viewModel:UserViewModel,debtViewModel:DebtViewModel, navController:NavHostController,backStackEntry:NavBackStackEntry,) {

    changeScreen(
        viewModel = viewModel,
        debtViewModel = debtViewModel,
        navController = navController,
        backStackEntry = backStackEntry
    )
}

@SuppressLint("NewApi")
@Composable
fun changeScreen(viewModel:UserViewModel,debtViewModel:DebtViewModel, navController:NavHostController,backStackEntry:NavBackStackEntry) {


    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var changeAmountValue by remember { mutableStateOf("") }

    val id = backStackEntry.arguments?.getString("id") ?: ""
    val sign = backStackEntry.arguments?.getString("negativesign") ?: ""

    viewModel.getUserById(id.toInt())

    val user by viewModel.userLiveData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(101, 105, 212))
            .padding(26.dp)
    ) {
        // Верхние кнопки (отмена и подтверждение)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Закрыть",
                    tint = Color.Red,
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = {
                viewModel.addDebt(
                    dateEdit = LocalDate.now().toString(),
                    amount = changeAmountValue.toDouble(),
                    isDebtReduce = sign.toBoolean(),
                    userId = id.toInt()
                )
                viewModel.updateUser(id.toInt(),
                    change = if (sign.toBoolean()) -changeAmountValue.toDouble() else changeAmountValue.toDouble()
                )
                navController.popBackStack()
            },
                enabled = changeAmountValue != ""
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Подтвердить",
                    tint = Color.Green,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода
        OutlinedTextField(
            value = changeAmountValue.toString(),
            onValueChange = { changeAmountValue = it },
            label = { Text("Сумма", color = Color.White) },
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            )

        // Устанавливаем фокус на текстовое поле при первом рендеринге
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}


@Composable
fun editDebt(
    viewModel: UserViewModel,
    debtViewModel: DebtViewModel,
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
) {
        editDebtUser(viewModel=viewModel,debtViewModel=debtViewModel,navController=navController,backStackEntry=backStackEntry)
}

@Composable
fun createDebtCreate(viewModel: UserViewModel, navController: NavHostController) {

    DebtScreen(viewModel=viewModel,navController=navController)

}

//@Composable
//fun userScreen2(viewModel: UserViewModel, navController: NavHostController) {
//
//    Screen(viewModel=viewModel, navController = navController)
//}

@Composable
fun Screen3(viewModel: UserViewModel,navController: NavController){

    Main_screen(viewModel=viewModel,navController=navController)
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun userScreen(viewModel: UserViewModel, navController: NavController){
//    val users by viewModel.users.collectAsState()
//
//
//    var name by remember { mutableStateOf("") }
//    var isOwe by remember { mutableStateOf(true) }
//
//
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { viewModel.addUSer(
//                name,
//                amount = TODO(),
//                comment = TODO(),
//                createdate = TODO(),
//                isSwitch = TODO(),
//                dateOfReturn = TODO()
//            ) }) {
//                Text("+")
//            }
//        },
//        topBar = {
//            TopAppBar(
//                title = { Text("Task Manager") },
//                actions = {
//                    Button(onClick = { navController.navigate("main3") }) {
//                        Text("Обновить")
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Box(Modifier.padding(innerPadding)) {
//
//            Column {
//                Switch(checked = isOwe, onCheckedChange = { isOwe = it })
//
//                OutlinedTextField(
//
//                    value = name,
//                    onValueChange = { name = it },
//                    label = { Text("Имя", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//            LazyColumn {
//                items(users.size) { user ->
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth().padding(8.dp)
//                    ) {
//                        Text(users[user].name)
//                        Button(onClick = { viewModel.deleteUser(users[user]) }) {
//                            Text("Удалить")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}



