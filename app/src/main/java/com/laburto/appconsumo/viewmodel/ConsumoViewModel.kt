package com.laburto.appconsumo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.laburto.appconsumo.data.local.database.AppDatabase
import com.laburto.appconsumo.data.model.Consumo
import com.laburto.appconsumo.data.repository.ConsumoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConsumoViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).consumoDao()
    private val repo = ConsumoRepository(dao)

    val consumos = repo.obtenerTodos()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun agregar(consumo: Consumo) = viewModelScope.launch {
        repo.insertar(consumo)
    }
}