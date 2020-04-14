package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultadoimc.*

class Resultadoimc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultadoimc)

        val pessoa = intent.extras?.get("pessoa") as Pessoa
        val imc = pessoa.peso/(pessoa.altura * pessoa.altura)

        txtResultado.text= "${pessoa.nome}, o seu IMC é de %.2f".format(imc)
    }
}
