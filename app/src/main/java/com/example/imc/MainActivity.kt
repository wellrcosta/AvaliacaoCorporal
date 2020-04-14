package com.example.imc

import android.app.AlertDialog
import android.content.DialogInterface
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

            try {
                var peso: Double = edtPeso.text.toString().toDouble()
                var altura: Double = edtAltura.text.toString().toDouble()


                val pessoa = Pessoa(edtNome.text.toString(), peso, altura)
                val intent = Intent(this, Resultadoimc::class.java)
                intent.putExtra("pessoa", pessoa as Serializable)
                startActivity(intent)
            }
            catch (err: Throwable) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("ERRO")
                builder.setMessage(err.message)
                builder.show()
            }
        })
    }
}
