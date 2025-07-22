package com.laburto.appconsumo.vista.formulario

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.laburto.appconsumo.data.model.Consumo
import com.laburto.appconsumo.viewmodel.ConsumoViewModel
import java.time.LocalDate

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.stringResource
import com.laburto.appconsumo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioConsumoScreen(
    navController: NavController,
    viewModel: ConsumoViewModel
) {
    // Estado para "Medidor"
    var numeroMedidor by remember { mutableStateOf("") }
    val tiposConsumo = listOf(
        stringResource(R.string.type_water),
        stringResource(R.string.type_light),
        stringResource(R.string.type_gas)
    )
    var tipoSeleccionado by remember { mutableStateOf(tiposConsumo.firstOrNull() ?: "") }
    var valor by remember { mutableStateOf("") }

    Scaffold(
        topBar = {

            TopAppBar(title = { Text(stringResource(R.string.title_meter_record)) })
        },

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(), // Para que el botón vaya al final
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo para el número de medidor
            OutlinedTextField(
                value = numeroMedidor,
                onValueChange = { numeroMedidor = it },
                label = { Text(stringResource(R.string.meter_label)) }, // <-- Nuevo stringResource
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))


            Text(
                text = stringResource(R.string.date_label, LocalDate.now().toString()), // <-- Nuevo stringResource y formato
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            // --- Sección de RadioButtons para el tipo de consumo ---
            Text(text = stringResource(R.string.meter_type_label), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            Column {
                tiposConsumo.forEach { tipo ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (tipo == tipoSeleccionado),
                            onClick = { tipoSeleccionado = tipo }
                        )
                        Text(
                            text = tipo,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            // --- Fin de la sección de RadioButtons ---

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = valor,
                onValueChange = { valor = it },
                label = { Text(stringResource(R.string.value_label)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            Spacer(Modifier.weight(1f))


            Button(
                onClick = {

                    if (numeroMedidor.isNotBlank() && tipoSeleccionado.isNotBlank() && valor.toDoubleOrNull() != null) {
                        viewModel.agregar(
                            Consumo(
                                tipo = tipoSeleccionado,
                                valor = valor.toDouble(),
                                fecha = LocalDate.now().toString(),
                                numeroMedidor = numeroMedidor
                            )
                        )
                        navController.popBackStack()
                    } else {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Altura del botón
            ) {
                Text(stringResource(R.string.register_measurement_button))
            }
        }
    }
}