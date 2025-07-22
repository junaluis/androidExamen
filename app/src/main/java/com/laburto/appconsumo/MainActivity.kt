package com.laburto.appconsumo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.laburto.appconsumo.vista.lista.ListaConsumosScreen
import com.laburto.appconsumo.vista.formulario.FormularioConsumoScreen
import com.laburto.appconsumo.viewmodel.ConsumoViewModel

class MainActivity : ComponentActivity() {

    private val consumoViewModel: ConsumoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNav(consumoViewModel)
            }
        }
    }

    @Composable
    private fun AppNav(viewModel: ConsumoViewModel) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "lista") {
            composable("lista") {
                ListaConsumosScreen(navController = navController, viewModel = viewModel)
            }
            composable("formulario") {
                FormularioConsumoScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}
