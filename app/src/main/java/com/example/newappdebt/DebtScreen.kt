package com.example.newappdebt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.Image
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
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newappdebt.mvvm.UserViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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


    val krestik: Painter = painterResource(id = R.drawable.krestik)
    val galka: Painter = painterResource(id = R.drawable.galka)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(101, 105, 212))
            .padding(top = 36.dp)
    ) {
        // Верхние кнопки (отмена и подтверждение)
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(modifier = Modifier.scale(1.1f),onClick = {

                navController.navigate("main3") }
            ,
                colors = IconButtonColors(
                    containerColor = Color(217, 217, 217),
                    contentColor = Color.Red,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.LightGray
                )
                ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Закрыть",
                    tint = Color.Red
                )
            }
            IconButton(modifier = Modifier.scale(1.1f), onClick = {

                viewModel.addUSer(
                    name,
                    inputamount.toDouble(),
                    comment.toString(),
                    createdate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString(),
                    swtch,
                    selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
                )
                navController.navigate("main3") },
                enabled = name.isNotBlank(),
                colors = IconButtonColors(
                    containerColor = Color(217, 217, 217),
                    contentColor = Color.Green,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.LightGray
                )
                ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Подтвердить",
                    tint = Color.Green
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

        val edit_calendar: Painter = painterResource(id = R.drawable.edit_calendar)
        // Выбор даты
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color(160, 163, 236), shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable {
                // При нажатии показываем диалог выбора даты
                showDatePicker = true
            }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(painter = edit_calendar, contentDescription = "choice date")


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
            Text(text = selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString(), fontSize = 16.sp, color = Color(47, 49, 109))
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Переключатель "Я ДАЮ / Я БЕРУ"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(160, 163, 236))
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
        Column(modifier = Modifier.padding(start = 36.dp, end = 36.dp)) {
            OutlinedTextField(

                value = name,
                onValueChange = {name = it},
                label = { Text("Имя", color = Color(229, 220, 252)) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent, // Убираем границу при фокусе
                    unfocusedBorderColor = Color.Transparent // Убираем границу в обычном состоянии
                )
            )
            Row(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(47, 49, 109))) {  }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = inputamount.toString(),
                onValueChange = {inputamount= it },
                label = { Text("Сумма", color = Color(229, 220, 252)) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent, // Убираем границу при фокусе
                    unfocusedBorderColor = Color.Transparent // Убираем границу в обычном состоянии
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),


                )
            Row(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(47, 49, 109))){}
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = comment,
                onValueChange = {comment=it},
                label = { Text("Комментарий к долгу", color = Color(229, 220, 252)) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent, // Убираем границу при фокусе
                    unfocusedBorderColor = Color.Transparent // Убираем границу в обычном состоянии
                )
            )
            Row(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(47, 49, 109))){}
        }
    }
}
