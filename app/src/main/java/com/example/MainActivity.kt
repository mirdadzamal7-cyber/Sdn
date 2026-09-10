package com.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.database.AppDatabase
import com.example.data.repository.SkuRepository
import com.example.ui.SkuViewModel
import com.example.ui.SkuViewModelFactory
import com.example.ui.screens.MainScoutScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    try {
      val database = AppDatabase.getDatabase(applicationContext)
      val repository = SkuRepository(database.skuDao(), database.profileDao(), database.studentDao())
      val viewModelFactory = SkuViewModelFactory(repository)

      setContent {
        MyApplicationTheme {
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
          ) {
            val viewModel: SkuViewModel = viewModel(factory = viewModelFactory)
            MainScoutScreen(viewModel = viewModel)
          }
        }
      }
    } catch (e: Throwable) {
      Log.e("MainActivity", "Error in onCreate", e)
      setContent {
        MyApplicationTheme {
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
          ) {
            Text(text = "Memuat data SKU...")
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(text = "Hello $name!", modifier = modifier)
}

