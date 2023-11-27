package com.santo.practicacuatro.ui.captura

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.santo.practicacuatro.databinding.FragmentCapturaBinding
import com.santo.practicacuatro.model.Liquor
import androidx.lifecycle.Observer
import com.google.zxing.integration.android.IntentIntegrator


class CapturaFragment : Fragment() {
    private lateinit var capturaMainViewModel: CapturaMainViewModel
    private lateinit var capturaBinding: FragmentCapturaBinding
    private lateinit var liquorAdapter: LiquorAdapter
    private var liquorsList = mutableListOf<Liquor?>()






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        capturaBinding = FragmentCapturaBinding.inflate(inflater,container,false)
        capturaMainViewModel = ViewModelProvider(this)[CapturaMainViewModel::class.java]
        // Inflate the layout for this fragment
        val view = capturaBinding.root


        capturaBinding.bottomScaner.setOnClickListener { initScanner() }







    val mensaje5observer = Observer<String> {mensaje5 ->
            Toast.makeText(requireActivity(), mensaje5, Toast.LENGTH_LONG).show()}
        capturaMainViewModel.mensaje5.observe(viewLifecycleOwner,mensaje5observer)

        capturaMainViewModel.liquorsList.observe(viewLifecycleOwner,){liquorsList ->
            liquorAdapter.appendItems(liquorsList)

        }
        capturaBinding.buttonQr.setOnClickListener {
            capturaMainViewModel.loadLiquors(capturaBinding.textInputQr.text.toString())
            //capturaMainViewModel.loquellego(capturaBinding.textInputQr.text.toString())
            //ComplaintRepository.capturaBinding.buttonQr.text.toString()
        }

       /* capturaMainViewModel.liquorErase.observe(viewLifecycleOwner,){

            liquorAdapter.appendItems(liquorsList)

        }*/

        //capturaMainViewModel.loadLiquors()



        liquorAdapter= LiquorAdapter(liquorsList, onItemClicked = {onLiquorItemClicked(it)}
            /*onItemLongCliked = {
            onLiquorLongItemClicked(it)
        }*/)

        capturaBinding.recyclerLiquors.apply {
            layoutManager=LinearLayoutManager(this@CapturaFragment.requireContext())
            adapter= liquorAdapter
            setHasFixedSize(false)
        }





        return view
    }

    private fun initScanner() {
        IntentIntegrator(requireActivity()).initiateScan()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                Log.d("lector","ok")


        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireActivity(), "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireActivity(), "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
                capturaBinding.textInputQr.setText(result.contents)


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)


        }
    }


    /* private fun onLiquorLongItemClicked(liquor: Liquor?) {
         capturaMainViewModel.deleteLiquor(liquor)

     }*/

    private fun onLiquorItemClicked(liquor: Liquor?) {

    }


}