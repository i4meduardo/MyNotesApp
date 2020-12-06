package com.example.mynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AppNotaActivity : AppCompatActivity() {
    var etTitulo: EditText?=null
    var etContenido: EditText?=null
    var btnAddNota: Button?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_compat)
        val app = applicationContext as NotaApp

        etTitulo = findViewById(R.id.eTitulo)
        etContenido = findViewById(R.id.etContenido)
        btnAddNota = findViewById(R.id.btnGuardar)

        btnAddNota!!.setOnClickListener {
            val title = etTitulo!!.text.toString()
            val content = etContenido!!.text.toString()

            val nota = Nota(0, title, content)
            val notas = listOf<Nota>(nota)

            lifecycleScope.launch {
                app.baseDeDatos.notaDao().insert(notas)
            }

            etTitulo!!.setText("")
            etContenido!!.setText("")
        }
    }
}