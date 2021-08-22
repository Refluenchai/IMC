package com.slicedwork.imc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slicedwork.imc.databinding.FragmentResultadoBinding
import kotlin.math.round

class ResultadoFragment : Fragment() {

    private lateinit var binding: FragmentResultadoBinding
    private lateinit var args: ResultadoFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultadoBinding.inflate(inflater, container, false)

        args = ResultadoFragmentArgs.fromBundle(requireArguments())
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        definirComponentes()
    }

    private fun definirComponentes() {
        val imc = "IMC: ${args.imc.toDouble().toFloat()}"
        val avaliacao = "AVALIAÇÂO: ${args.avaliacao}"
        val grau = "GRAU: ${args.grau}"

        with(binding) {
            imgAvaliacao.setImageResource(args.imgAvaliacao)
            txtImc.text = imc
            txtAvaliacao.text = avaliacao
            txtGrau.text = grau
        }
    }
}