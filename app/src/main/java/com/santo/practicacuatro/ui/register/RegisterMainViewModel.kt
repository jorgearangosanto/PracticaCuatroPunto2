package com.santo.practicacuatro.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santo.practicacuatro.data.ResourceRemote
import com.santo.practicacuatro.data.UserRepository
import com.santo.practicacuatro.model.Uses
//import emailVaidator
import kotlinx.coroutines.launch

class RegisterMainViewModel: ViewModel() {

    private var userRepository = UserRepository()
    private val _registerSuccess:MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess : LiveData<Boolean> = _registerSuccess

    val mensaje5: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    fun validateRegister(email: String, contrasena: String, repcontrasena: String, rut: String) {
        if (email.isEmpty() || contrasena.isEmpty() || repcontrasena.isEmpty()) {
            mensaje5.value = "debe digitar correo y contrasena"
        } else {
            if (contrasena != repcontrasena) {
                mensaje5.value = "contrasenas diferentes"
            } else {
                if (contrasena.length < 6) {
                    mensaje5.value = "la contrasena debe ser de mas de 6 digitos"
                } /*else {
                   if (!emailVaidator(email)) {
                        mensaje5.value = "el correo electronico esta mal escrito"
                    }*/ else {

                        viewModelScope.launch {
                            val result  = userRepository.registerUser(email,contrasena)
                            result.let {resourceRemote ->
                                when(resourceRemote){
                                   is ResourceRemote.Success ->{
                                       val uid = result.data
                                       uid?.let { Log.d("uid User", it) }
                                       //val user = User(uid,email,rut)
                                       val user = Uses(uid,email,rut)
                                       createUser(user)
                                       //_registerSuccess.postValue(true)
                                       //mensaje5.postValue ("Usuario creado exitosamente")
                                   }
                                    is ResourceRemote.Error->{
                                        var msg = result.message
                                        when(msg){
                                            "The email address is already in use by another account."-> msg= "Ya existe una cuenta con ese correo"
                                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred."-> msg=" conectese a una red"
                                        }
                                        mensaje5.postValue (msg)

                                    }
                                    else->{
                                        //no use
                                    }

                                }
                                
                            }
                        }
                    }
                }
            }
        }

    private fun createUser(user: Uses) {
        viewModelScope.launch{
            val result  = userRepository.createUser(user)
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success ->{
                        _registerSuccess.postValue(true)
                        mensaje5.postValue ("Usuario creado exitosamente")

                    }
                    is ResourceRemote.Error->{
                        var msg = result.message
                        mensaje5.postValue (msg)

                    }else ->{
                        //dont use
                    }


                }
                
            }
        }

    }


}


