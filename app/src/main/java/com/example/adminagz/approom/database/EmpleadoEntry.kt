package com.example.adminagz.approom.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "empleado")
data class EmpleadoEntry (
        @PrimaryKey()
        var id:Long = 0 ,
        var Nombre:String,
        var Domicilio:String,
        var Sueldo: Float,
        @ColumnInfo(name = "Actualizado_En")
        var ActualizadoEn:Date
)