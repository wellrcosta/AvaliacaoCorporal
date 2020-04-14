package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class Pessoa (val nome: String, val peso: Double, val altura: Double) : Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcularIMC.setOnClickListener(View.OnClickListener {
        val pessoa = Pessoa(edtNome.text.toString(), edtPeso.text.toString().toDouble(),edtAltura.text.toString().toDouble())
            val intent = Intent(this, Resultadoimc::class.java)
           intent.putExtra("pessoa", pessoa as Serializable)
            startActivity(intent)
        })
    }
}
