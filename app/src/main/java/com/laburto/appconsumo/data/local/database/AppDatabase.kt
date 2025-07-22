package com.laburto.appconsumo.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.laburto.appconsumo.data.local.dao.ConsumoDao
import com.laburto.appconsumo.data.model.Consumo

@Database(entities = [Consumo::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun consumoDao(): ConsumoDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "consumos.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
