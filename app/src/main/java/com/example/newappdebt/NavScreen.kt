//package com.example.newappdebt
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//@Preview
//@Composable
//fun App(){
//
//
//    val navController = rememberNavController()
//
//    NavHost(navController,
//          startDestination = "main1"
//        ){
//        composable("main1"){Main1(navController)}
//        composable("main2"){ Main2(navController)}
//        composable("newRecord"){ newRecordScreen(navController) }
//        composable("edit"){editScreen(navController)}
//    }
//
//}
//
//
//
//@Composable
//fun Main1(navController: NavHostController) {
//
//
//    main_screen_1(navController)
//
//
//
//}
//
//@Composable
//fun Main2(navController: NavHostController) {
//
//    main_screen_2(navController)
//
//
//
//
//}
//
//@Composable
//fun newRecordScreen(navController: NavHostController){
//
//     DebtScreen(navController)
//
//}
//
//@Composable
//fun editScreen(navController: NavHostController) {
//
//    Text("")
//    Button(onClick = {navController.popBackStack()}) {
//        Text("НАзад")
//    }
//
//}