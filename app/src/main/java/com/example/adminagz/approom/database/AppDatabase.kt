package com.example.adminagz.approom.database

import android.arch.persistence.room.* // Todas las librerias de Room
import android.content.Context

// Permite configurar parametros de la base de datos
@Database(entities = arrayOf(EmpleadoEntry::class), version = 1,exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase(){
    // Similar metodo static dentrde la clase
    companion object {
        private  var Instance: AppDatabase?= null

        fun getInstance(context: Context): AppDatabase?{
            if (Instance == null){
                synchronized(AppDatabase::class){
                    Instance = Room.databaseBuilder(
                            context.applicationContext ,
                            AppDatabase::class.java,
                            "todoEmpleado.db"
                    ).build()
                }
            }
            return Instance
        }
    }
    // Agrega las Opciones CRUD definidas en el paso anterior
    abstract fun empleadoDao() : EmpleadoDAO
}