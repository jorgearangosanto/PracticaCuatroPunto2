package com.santo.practicacuatro.ui.denuncia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santo.practicacuatro.R
import com.santo.practicacuatro.databinding.FragmentDenunciaBinding


class DenunciaFragment : Fragment() {
    private lateinit var denunciaViewModel: DenunciaMainViewModel
    private lateinit var denunciaBinding: FragmentDenunciaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        denunciaBinding = FragmentDenunciaBinding.inflate(inflater,container,false)
        denunciaViewModel = ViewModelProvider(this)[DenunciaMainViewModel::class.java]
        // Inflate the layout for this fragmentv
        val view = denunciaBinding.root

        val mensaje5observer = Observer<String> {mensaje5 ->
            Toast.makeText(requireActivity(), mensaje5, Toast.LENGTH_LONG).show()}
        denunciaViewModel.mensaje5.observe(viewLifecycleOwner,mensaje5observer)

        denunciaViewModel.denunciaSuccess.observe(viewLifecycleOwner){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }



        with(denunciaBinding){
            buttonDenunciar.setOnClickListener {
                denunciaViewModel.vaidateFields(
                    motivoEditText.text.toString(),
                    municipioEditext.text.toString(),
                    nombreEsatblecimientoEditext.text.toString(),
                    direccionEdittex.text.toString(),
                    telefonoEdittext.text.toString()

                )

            }
        }




        return view

        }
}


