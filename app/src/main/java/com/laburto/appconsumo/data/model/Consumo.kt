package com.laburto.appconsumo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consumos")
data class Consumo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String,
    val valor: Double,
    val fecha: String,
    val numeroMedidor: String
)
