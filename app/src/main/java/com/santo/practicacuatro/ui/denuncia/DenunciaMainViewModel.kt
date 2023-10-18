package com.santo.practicacuatro.ui.denuncia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santo.practicacuatro.data.ComplaintRepository
import com.santo.practicacuatro.data.ResourceRemote
import com.santo.practicacuatro.model.Complaint
import com.santo.practicacuatro.model.Uses
import kotlinx.coroutines.launch

class DenunciaMainViewModel: ViewModel() {

    val complaintRepository = ComplaintRepository()

    val mensaje5: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    private val _denunciaSuccess:MutableLiveData<Boolean> = MutableLiveData()
    val denunciaSuccess : LiveData<Boolean> = _denunciaSuccess

    fun vaidateFields(motivo: String, municipio: String, nombreEstablecimiento: String, direccion: String, telefono: String) {
        if (motivo.isEmpty() || municipio.isEmpty() || telefono.isEmpty()){
            mensaje5.value = "debe digitar motivo,municipio  y telefono"

        }else{
            val denuncia = Complaint(motivo = motivo, estableciminto= nombreEstablecimiento, direccion = direccion, telefono = telefono)

            viewModelScope.launch{

                val result  = complaintRepository.createDenuncia(denuncia)
                result.let {resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success ->{
                            mensaje5.postValue ("Denuncia guardada con exito")
                            _denunciaSuccess.postValue(true) }

                        is ResourceRemote.Error->{
                            var msg = result.message

                            mensaje5.postValue (msg)

                        }
                        else->{
                            //no use
                        }


                    }
        }
}}}}