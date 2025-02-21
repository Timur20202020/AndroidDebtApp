package com.example.newappdebt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.newappdebt.data.Debt_History
import com.example.newappdebt.mvvm.DebtViewModel
import com.example.newappdebt.mvvm.UserViewModel
import com.example.newappdebt.view.changeAmountscreen
import java.time.LocalDate
import kotlin.math.sign

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun EditSreenExperimental(
    viewModel: UserViewModel,
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    debtViewModel: DebtViewModel
) {

    val users by viewModel.users.collectAsState()


    val debthistory by debtViewModel.debtHistory.collectAsState()




//    val debt = backStackEntry.arguments?.getString("debt") ?: ""

    val id = backStackEntry.arguments?.getString("id") ?: ""


    // десериализация
//    val user = Json.decodeFromString<User>(debt ?: "")

    var textinput by remember { mutableStateOf("") }
//    val amount = user.amount


    viewModel.getUserById(id.toInt())

    val user by viewModel.userLiveData.collectAsState()

    viewModel.getDebtByUser(id.toInt())
    val debthistory2 by viewModel.debtFlowHistory.collectAsState()


    val openDialog = remember { mutableStateOf(false) }
    var sign by remember { mutableStateOf(true) }


   if (openDialog.value){
       Dialog(
           onDismissRequest = {},
           properties = DialogProperties()
       ) {
           Button(onClick = {openDialog.value=false}) {
               Text("!")
           }
           val focusRequester = remember { FocusRequester() }
           val focusManager = LocalFocusManager.current
           var changeAmountValue by remember { mutableStateOf("") }

//           val id = backStackEntry.arguments?.getString("id") ?: ""
//           val sign = backStackEntry.arguments?.getString("negativesign") ?: ""

//           viewModel.getUserById(id.toInt())
//
//           val user by viewModel.userLiveData.collectAsState()

           Column(
               modifier = Modifier
                   .background(Color(101, 105, 212))
                   .padding(26.dp)
           ) {
               // Верхние кнопки (отмена и подтверждение)
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   IconButton(onClick = {
                       openDialog.value= false
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
                           isDebtReduce = sign,
                           userId = id.toInt()
                       )
                       viewModel.updateUser(id.toInt(),
                           change = if (sign) -changeAmountValue.toDouble() else changeAmountValue.toDouble()
                       )
                       openDialog.value=false
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
   }


Column(modifier = Modifier.padding(24.dp) ) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(
                    Color(
                        101,
                        105, 212
                    )
                ).padding(24.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Назад",
                    tint = Color.White,
                    modifier = Modifier.weight(0.2F)
                )}
//                Spacer(modifier = Modifier.weight(0.6F))
                user?.let {
                    Text(
                        it.name,
                        modifier = Modifier.weight(0.6F),
                        textAlign = TextAlign.Center,
                        color = Color(229, 220, 252),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500)
                    )
                }

                IconButton(
                    onClick = { viewModel.deleteUserByID(id.toInt()); navController.popBackStack()}){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить",
                    tint = Color.White,
                    modifier = Modifier.weight(0.2F)
                )}
            }
            Spacer(modifier = Modifier.size(44.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(0.2F)) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Дата открытия",
                            tint = Color.White,
                            modifier = Modifier.weight(0.2F)
                        )
                        user?.let { Text(it.dateTime, color = Color.White) }

                    }
                    Text("открытие займа", color = Color.White)
                }
                Spacer(modifier = Modifier.weight(0.6F))

                Column(modifier = Modifier.weight(0.2F)) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Дата закрытия",
                            tint = Color.White,
                            modifier = Modifier.weight(0.2F)
                        )

                        user?.let { Text(it.dateOfReturn, color = Color.White) }
                    }
                    Text("закрытие займа", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.size(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${user?.amount?.toInt()}₽", color = Color.White,
                        fontSize = 35.sp, fontWeight = FontWeight(700))
                    Text(if (user?.isSwitch==true) "Я ДОЛЖЕН" else "МНЕ ДОЛЖНЫ",
                        color = Color(229, 220, 252),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400)
                        )
                    user?.comment?.let { Text(it, color = Color.White, fontSize = 12.sp,
                        fontWeight = FontWeight(400)
                    ) }
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(

                    onClick = {

                        sign=true
                        openDialog.value = true
//                        navController.navigate("changeAmount/${id}/${true}")
                              },
                    colors = IconButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Red,
                        disabledContentColor = Color.Green
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.weight(0.2F)

                    )
                }
//              Button(modifier = Modifier.weight(0.2f).background(Color.Red, shape = CircleShape), onClick = {}) { "-"}
                Spacer(modifier = Modifier.weight(0.8f))
//              Button(modifier = Modifier.weight(0.2f).background(Color.Red, shape = CircleShape), onClick = {}) { "-"}

                IconButton(
                    onClick = {
                        sign=true
                        openDialog.value = true
//                              navController.navigate("changeAmount/${id}/${false}")

                    },
                    colors = IconButtonColors(
                        containerColor = Color.Green,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Green,
                        disabledContentColor = Color.Green
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.weight(0.2F)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
//            Text("${debthistory2.size}")
             Row(modifier = Modifier.background(Color(47, 49, 109))) {
              DebtHistoryList(debthistory2)
          }

        }
    }


 }
}

@Composable
fun DebtHistoryList(debts:List<Debt_History>) {

    if (debts.isEmpty()){
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(text = "Истории нету")
        }
    }
    else{

        LazyColumn(modifier = Modifier.fillMaxSize().background(Color(47, 49, 109)),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(debts.size) { debt ->
                DebtHistoryItem(debt = debts[debt],)
            }

        }
    }

}

@Composable
fun DebtHistoryItem(debt: Debt_History) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)

        .background(Color.Transparent, shape = RoundedCornerShape(28.dp))
        .clickable { }
    ) {

        Column(modifier = Modifier
            .background(Color(47, 49, 109))
            .padding(6.dp)) {
            Row() {



                val handshake: Painter = painterResource(id = R.drawable.handshake)
                val agent: Painter = painterResource(id = R.drawable.real_estate_agent)

                val debticon = if (debt.isDebtReduce == true){Image(
                    painter = handshake,
                    contentDescription = "debt decrease"
                )}else{
                    Image(
                        painter = agent,
                        contentDescription = "debt increase")
                }
                Text(text = if (debt.isDebtReduce == true){" уменьшение долга"}else{" увеличение долга"}, modifier = Modifier.weight(0.9f),
                    fontWeight = FontWeight(500),
                    color = Color(255, 255, 255),
                    fontSize = 18.sp)

                Text(text = if (debt.isDebtReduce == true){"-${debt.amount}"}else{"${debt.amount}"}, fontWeight = FontWeight(700),
                    color = Color(255, 255, 255),
                    fontSize = 22.sp
                )



            }
            Row() {
                Spacer(modifier = Modifier.weight(0.9f))
                Column() {

                    Text(text = "${debt.dateOfEdit} ${debt.userId}", fontWeight = FontWeight(400),
                        color = Color(229, 220, 252),
                        fontSize = 12.sp,
                       )
                }
            }
            Row() {

                Spacer(modifier = Modifier.weight(0.9f))
                Column() {
                    Text(text = "", fontWeight = FontWeight(500),
                        color = Color(229, 220, 252),
                        fontSize = 12.sp)

                }
            }
        }

    }

}

