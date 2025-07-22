package com.laburto.appconsumo.vista.lista

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.laburto.appconsumo.viewmodel.ConsumoViewModel
import com.laburto.appconsumo.data.model.Consumo

// Importaciones necesarias para la anotación experimental y los iconos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
// Importaciones para los iconos de tipo de consumo
import androidx.compose.material.icons.filled.WaterDrop // Para Agua
import androidx.compose.material.icons.filled.Lightbulb // Para Luz
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.ui.graphics.vector.ImageVector // Para la función auxiliar


import androidx.compose.ui.Alignment
import androidx.compose.material.icons.filled.HelpOutline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaConsumosScreen(
    navController: NavController,
    viewModel: ConsumoViewModel
) {
    val lista by viewModel.consumos.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Registros Consumo") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("formulario") }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(lista) { consumo ->
                ConsumoCard(consumo = consumo)
            }
        }
    }
}


@Composable
fun ConsumoCard(consumo: Consumo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp), // Ajustamos el padding para la tarjeta
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Centramos verticalmente los elementos
        ) {
            // Icono basado en el tipo de consumo
            Icon(
                imageVector = getIconForConsumoType(consumo.tipo),
                contentDescription = "Tipo: ${consumo.tipo}",
                modifier = Modifier.size(24.dp) // Tamaño del icono
            )

            Spacer(Modifier.width(16.dp)) // Espacio entre el icono y el texto

            Column(modifier = Modifier.weight(1f)) { // Columna para el tipo y valor
                Text(
                    text = "${consumo.tipo}: ${consumo.valor}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Text(
                text = consumo.fecha,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Función auxiliar para obtener el icono según el tipo de consumo
fun getIconForConsumoType(tipo: String): ImageVector {
    return when (tipo) {
        "Agua" -> Icons.Default.WaterDrop
        "Luz" -> Icons.Default.Lightbulb
        "Gas" -> Icons.Default.LocalFireDepartment
        else -> Icons.Default.HelpOutline // Un icono por defecto si el tipo no coincide
    }
}