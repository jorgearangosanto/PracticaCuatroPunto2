package com.santo.practicacuatro.ui.newliquor

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santo.practicacuatro.R

class NewLiquorFragment : Fragment() {

    companion object {
        fun newInstance() = NewLiquorFragment()
    }

    private lateinit var viewModel: NewLiquorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_liquor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewLiquorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}