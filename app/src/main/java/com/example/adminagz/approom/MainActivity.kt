package com.example.adminagz.approom

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.adminagz.approom.database.AppDatabase
import com.example.adminagz.approom.database.EmpleadoEntry
import com.example.adminagz.approom.helper.doAsync
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnlista(v:View)
    {
        var inte = Intent(this,MainActivityLista::class.java)
        startActivity(inte)
    }

    fun Agregar(v:View)
    {
        val suel:Float=txtSueldo.text.toString().toFloat()
        val em:EmpleadoEntry=EmpleadoEntry(id= txtID.text.toString().toLong(),Nombre = txtNom.text.toString(),Domicilio = txtDom.text.toString(),Sueldo =suel,ActualizadoEn = Date ())
        doAsync{
            AppDatabase.getInstance(this)!!.empleadoDao().insertEmpleado(em)
            runOnUiThread {
                limpia()
            }
        }.execute()
    }

    fun limpia()
    {
        txtID.setText("")
        txtNom.setText("")
        txtDom.setText("")
        txtSueldo.setText("0")
        txtID.requestFocus()
    }

    fun Buscar (v:View)
    {
        val ID:Long=txtID.text.toString().toLong()
        doAsync {
            val em= AppDatabase.getInstance(this@MainActivity)?.empleadoDao()?.loadEmpleadoById(ID)
            runOnUiThread {
                if (em == null)
                {
                    Toast.makeText(this,"No se encontro",Toast.LENGTH_LONG).show()

                }
                else
                {
                    llenaCamposUI(em!!)
                }

            }
        }.execute()
    }
    fun Actualiza(v:View)
    {
        val suel:Float=txtSueldo.text.toString().toFloat()
        val em:EmpleadoEntry=EmpleadoEntry(id= txtID.text.toString().toLong(),Nombre = txtNom.text.toString(),Domicilio = txtDom.text.toString(),Sueldo =suel,ActualizadoEn = Date ())
        doAsync{
            AppDatabase.getInstance(this)!!.empleadoDao().updateEmpleado(em)
            runOnUiThread {
                limpia()
            }
        }.execute()

    }

    fun Elimina(v:View)
    {
        //val ID:Long=txtID.text.toString().toLong()
        val suel:Float=txtSueldo.text.toString().toFloat()
        val em:EmpleadoEntry=EmpleadoEntry(id= txtID.text.toString().toLong(),Nombre = txtNom.text.toString(),Domicilio = txtDom.text.toString(),Sueldo =suel,ActualizadoEn = Date ())
        doAsync{
            AppDatabase.getInstance(this)!!.empleadoDao().deleteEmpleado(em)
            runOnUiThread {
                limpia()
            }
        }.execute()

    }
    private fun llenaCamposUI(empleado: EmpleadoEntry){
        if (empleado == null) return
        txtNom.setText(empleado.Nombre)
        txtDom.setText(empleado.Domicilio)
        txtSueldo.setText(empleado.Sueldo.toString())
    }
}
