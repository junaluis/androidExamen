package com.laburto.appconsumo.data.local.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao
import com.laburto.appconsumo.data.model.Consumo
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsumoDao {
    @Insert suspend fun insertar(consumo: Consumo)

    @Query("SELECT * FROM consumos ORDER BY fecha DESC")
    fun obtenerTodos(): Flow<List<Consumo>>
}
