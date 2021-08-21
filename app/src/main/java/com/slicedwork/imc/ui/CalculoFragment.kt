package com.slicedwork.imc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.somatador.R
import com.example.somatador.databinding.FragmentCalculoBinding

class CalculoFragment : Fragment() {

    private lateinit var binding: FragmentCalculoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {

            focarComponente(editAltura)

            btnCalcular.setOnClickListener {
                if (camposPreenchidos(editAltura) && camposPreenchidos(editPeso)) {
                    val altura = editAltura.text.toString().toFloat()
                    val peso = editPeso.text.toString().toFloat()

                    val imc = peso / (altura * altura)

                    val (avaliacao, grau, imgAvaliacao) = pegarImcAvaliacao(imc)

                    irParaResultado(it, imc, avaliacao, grau, imgAvaliacao)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        limparComponentes()
    }

    private fun focarComponente(editAltura: EditText) = editAltura.requestFocus()

    private fun camposPreenchidos(editText: EditText): Boolean {
        if (editText.text.isEmpty()) {
            editText.error = getString(R.string.lbl_erro_preenchimento)
            return false
        }
        return true
    }

    private fun pegarImcAvaliacao(imc: Float): Triple<String, String, Int> {
        return when (imc.toInt()) {
            in 0..18 -> Triple("MAGREZA", "0", R.drawable.ic_magreza)
            in 18..24 -> Triple("NORMAL", "0", R.drawable.ic_normal)
            in 25..29 -> Triple("SOBREPESO", "0", R.drawable.ic_sobrepeso)
            in 30..35 -> Triple("OBESIDADE", "I", R.drawable.ic_obesidade)
            in 36..40 -> Triple("OBESIDADE SEVERA", "II", R.drawable.ic_obesidade_severa)
            else -> Triple("OBESIDADE GRAVE", "III", R.drawable.ic_obesidade_morbida)
        }
    }

    private fun irParaResultado(
        view: View,
        imc: Float,
        avaliacao: String,
        grau: String,
        imgAvaliacao: Int
    ) {
        view.findNavController().navigate(
            CalculoFragmentDirections.actionCalculoFragmentToResultadoFragment(
                imc,
                avaliacao,
                grau,
                imgAvaliacao
            )
        )
    }

    private fun limparComponentes() {
        with(binding) {
            editAltura.text.clear()
            editPeso.text.clear()
        }
    }
}