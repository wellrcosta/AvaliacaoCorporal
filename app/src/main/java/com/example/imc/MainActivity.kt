package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.lang.Exception
import java.lang.Math.log
import java.lang.Math.log10
import kotlin.math.ln

class Pessoa (val nome: String,
              val peso: Double,
              val sexo: String,
              val val1: Double,
              val val2: Double,
              val val3: Double) : Serializable{

    fun calcularGC(): String {
        if (sexo == "Masculino"){
            val densidade : Double = 1.17136 - 0.06706 * log10(val1+val2+val3)
            val percG : Double = ((4.95/densidade)-4.50)*100
            val gordAb : Double = (percG/100) * peso
            val massaMagra : Double = (peso) - gordAb
            val percMassaMagra : Double = 100 - percG
            val descPercG : String = when(percG){
                in Double.NEGATIVE_INFINITY..4.99 -> "magrinho"
                in 5.0..8.99 -> "abaixo da média"
                in 9.0..16.99 -> "na média"
                in 17.0..24.99 -> "acima da média"
                in 25.0..Double.POSITIVE_INFINITY -> "obeso ein meu"
                else -> "Algo deu errado, contate o desenvolvedor do sistema"
            }

            return ("Segue o Relatório de $nome, que está $descPercG:\n\n" +
                    "Densidade Corporal: %.2f").format(densidade) +
                    "\nPercentual de Gordura: %.2f".format(percG) + '%' +
                    "\nGordura Absoluta: %.2f".format(gordAb) +
                    "\nMassa Magra: %.2f".format(massaMagra) +
                    "\nPercentual de Massa Magra: %.2f".format(percMassaMagra) + '%' +
                    "\nPeso Ideal: %.2f".format(massaMagra/0.85) +
                    "\nPeso em Excesso: %.2f".format(peso - (massaMagra/0.85))

        }else{
            val densidade : Double = 1.16650 - 0.07063 * log10(val1+val2+val3)
            val percG : Double = ((4.95/densidade)-4.50)*100
            val gordAb: Double = (percG/100) * peso
            val massaMagra: Double = (peso) - gordAb
            val percMassaMagra: Double = 100 - percG
            val descPercG : String = when(percG){
                in Double.NEGATIVE_INFINITY..8.99 -> "magrinha"
                in 9.0..14.99 -> "abaixo da Média"
                in 15.0..22.99 -> "na média"
                in 23.0..29.99 -> "acima da média"
                in 30.0..Double.POSITIVE_INFINITY -> "obesa ein minha"
                else -> "Algo deu errado, contate o desenvolvedor do sistema"
            }

            return ("Segue o Relatório de $, que está $descPercG::\n\n" +
                    "Peso: %.2f".format(peso) +
                    "\nDensidade Corporal: %.2f").format(densidade) +
                    "\nPercentual de Gordura: %.2f".format(percG) + '%' +
                    "\nGordura Absoluta: %.2f".format(gordAb) +
                    "\nMassa Magra: %.2f".format(massaMagra) +
                    "\nPercentual de Massa Magra: %.2f".format(percMassaMagra) + '%' +
                    "\nPeso Ideal: %.2f".format(massaMagra/0.75) +
                    "\nPeso em Excesso: %.2f".format(peso - (massaMagra/0.75))
        }
    }

}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edt1.visibility = View.INVISIBLE
        edt2.visibility = View.INVISIBLE
        edt3.visibility = View.INVISIBLE
        txt1.visibility = View.INVISIBLE
        txt2.visibility = View.INVISIBLE
        txt3.visibility = View.INVISIBLE
        btnCalcular.visibility = View.INVISIBLE
        var textRadio: String = ""

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                edt1.visibility = View.VISIBLE
                edt2.visibility = View.VISIBLE
                edt3.visibility = View.VISIBLE
                txt1.visibility = View.VISIBLE
                txt2.visibility = View.VISIBLE
                txt3.visibility = View.VISIBLE
                btnCalcular.visibility = View.VISIBLE
                textRadio = radio.text.toString()

                if (radio.text== "Masculino"){
                    txt1.text="Tríceps"
                    txt2.text="Supra-Ilíaca"
                    txt3.text="Abdominal"
                }else{
                    txt1.text="Subescapular"
                    txt2.text="Supra-Ilíaca"
                    txt3.text="Coxa"
                }
            })


            btnCalcular.setOnClickListener(View.OnClickListener {
                try {
                val pessoa = Pessoa(
                    edtNome.text.toString(),
                    edtPeso.text.toString().toDouble(),
                    textRadio,
                    edt1.text.toString().toDouble(),
                    edt2.text.toString().toDouble(),
                    edt3.text.toString().toDouble()
                )
                val intent = Intent(this, Resultadoimc::class.java)
                intent.putExtra("pessoa", pessoa as Serializable)
                startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(this, "Por favor, Preencha todos os campos", Toast.LENGTH_LONG).show()
                }
            })

    }
}
