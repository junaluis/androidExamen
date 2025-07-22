package com.laburto.appconsumo.data.repository



import com.laburto.appconsumo.data.local.dao.ConsumoDao // <-- ¡IMPORTANTE!
import com.laburto.appconsumo.data.model.Consumo    // <-- ¡IMPORTANTE!

class ConsumoRepository(private val dao: ConsumoDao) {
    fun obtenerTodos() = dao.obtenerTodos()
    suspend fun insertar(consumo: Consumo) = dao.insertar(consumo)
}