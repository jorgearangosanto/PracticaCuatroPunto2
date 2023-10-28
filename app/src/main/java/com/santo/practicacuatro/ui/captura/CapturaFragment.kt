package com.santo.practicacuatro.ui.captura

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.santo.practicacuatro.R
import com.santo.practicacuatro.databinding.FragmentCapturaBinding
import com.santo.practicacuatro.model.Liquor
import com.santo.practicacuatro.ui.denuncia.DenunciaMainViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController


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



        val mensaje5observer = Observer<String> {mensaje5 ->
            Toast.makeText(requireActivity(), mensaje5, Toast.LENGTH_LONG).show()}
        capturaMainViewModel.mensaje5.observe(viewLifecycleOwner,mensaje5observer)

        capturaMainViewModel.liquorsList.observe(viewLifecycleOwner,){liquorsList ->
            liquorAdapter.appendItems(liquorsList)

        }
        capturaBinding.buttonQr.setOnClickListener {
            capturaMainViewModel.loadLiquors()
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

   /* private fun onLiquorLongItemClicked(liquor: Liquor?) {
        capturaMainViewModel.deleteLiquor(liquor)

    }*/

    private fun onLiquorItemClicked(liquor: Liquor?) {

    }


}