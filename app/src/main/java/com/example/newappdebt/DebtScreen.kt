package com.example.newappdebt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newappdebt.mvvm.UserViewModel
import java.time.LocalDate


@SuppressLint("NewApi")
@Composable
fun DebtScreen(viewModel: UserViewModel,navController: NavHostController) {


    val users by viewModel.users.collectAsState()

    var name by remember {   mutableStateOf("") }
    var inputamount by remember  { mutableStateOf("") }
    var swtch by remember { mutableStateOf(true) }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) } // Храним выбранную дату
    var showDatePicker by remember { mutableStateOf(false) } // Показывать ли диалог выбора даты


    var comment by remember {   mutableStateOf("") }



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

                navController.navigate("main3") }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Закрыть",
                    tint = Color.Red,
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = {

                viewModel.addUSer(
                    name,
                    inputamount.toDouble(),
                    comment.toString(),
                    createdate = LocalDate.now().toString(),
                    swtch,
                    selectedDate.toString()
                )
                navController.navigate("main3") },
                enabled = name.isNotBlank()
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

        // Заголовок
        Text(
            text = "Планируемая дата возврата",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Выбор даты
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable {
                // При нажатии показываем диалог выбора даты
                showDatePicker = true
            }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Календарь",
                tint = Color.Black
            )
            if (showDatePicker) {
                val context = LocalContext.current
                val currentDate = selectedDate
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth) // Обновляем выбранную дату
                        showDatePicker = false // Закрываем диалог
                    },
                    currentDate.year,
                    currentDate.monthValue - 1,
                    currentDate.dayOfMonth
                )

                // Показать диалог
                LaunchedEffect(true) {
                    datePickerDialog.show()
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = selectedDate.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Переключатель "Я ДАЮ / Я БЕРУ"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(160, 160, 200), shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Я ДАЮ", color = Color(47, 49, 109), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = swtch, onCheckedChange = { swtch = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Я БЕРУ", color = Color(229, 220, 252), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Поля ввода
        Column {
            OutlinedTextField(

                value = name,
                onValueChange = {name = it},
                label = { Text("Имя", color = Color.White) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = inputamount.toString(),
                onValueChange = {inputamount= it },
                label = { Text("Сумма", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),


                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = comment,
                onValueChange = {comment=it},
                label = { Text("Комментарий к долгу", color = Color.White) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
