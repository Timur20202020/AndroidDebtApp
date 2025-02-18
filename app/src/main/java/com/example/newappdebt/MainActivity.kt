package com.example.newappdebt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.newappdebt.di.AppModule
import com.example.newappdebt.mvvm.DebtViewModel
import com.example.newappdebt.mvvm.DebtViewModelFactory
import com.example.newappdebt.mvvm.UserViewModel
import com.example.newappdebt.mvvm.UserViewModelFactory
import com.example.newappdebt.ui.theme.NewAppDEbtTheme
import com.example.newappdebt.view.NavGraf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        // Инициализируем БД и репозиторий

//        val db = AppModule.provideDatabase(this)
//        val repository = AppModule.provideRepository(db)
//
//
//        // Создаём ViewModel вручную (в реальном приложении использовать ViewModelProvider)
//        val viewModel = UserViewModel(repository)


        val db by lazy { AppModule.provideDatabase(this) }
        val repository by lazy { AppModule.provideRepository(db) }

        val debtRepository by lazy { AppModule.provideDebtRepository(db) }



        val viewModel: UserViewModel by viewModels {
            UserViewModelFactory(
                repository

            )
        }

        val debtViewModel = DebtViewModel(debtRepository)
//        вызывало ошибку
//        val debtViewModel:DebtViewModel by viewModels{
//            DebtViewModelFactory(
//                debtRepository
//            )
//        }
        setContent {
            NewAppDEbtTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

//                    listViewModel(viewModel, debtViewModel)
                     NavGraf(viewModel,debtViewModel)
                }
            }
        }
    }
}


