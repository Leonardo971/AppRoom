package com.example.adminagz.approom.helper

import android.os.AsyncTask

// Clase para mandar ejecutar instrucciones de Room en un hilo
class doAsync(val handler:() -> Unit) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        handler()
        return null
    }
}