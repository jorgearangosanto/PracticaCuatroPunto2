package com.santo.practicacuatro.ui.login

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santo.practicacuatro.data.ResourceRemote
import com.santo.practicacuatro.data.UserRepository
import com.santo.practicacuatro.ui.bottom.BottomNavigationActivity
import emailVaidator
import kotlinx.coroutines.launch

class LoginMainViewModel: ViewModel() {
    private var userRepository = UserRepository()

    private val _registerSuccess:MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess : LiveData<Boolean> = _registerSuccess



    val mensaje5: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }





    fun validateFields(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()){
            mensaje5.value = "debe digitar correo y contrasena"
        }else{
            if (password.length<6){
                mensaje5.value = "la contrasena debe ser de mas de 6 digitos"
            }else{
                if (!emailVaidator(email)) {
                    mensaje5.value = "el correo electronico esta mal escrito"
                }
                else {
                    if (!emailVaidator(email)) {
                        mensaje5.value = "el correo electronico esta mal escrito"
                    }
                    else{
                       viewModelScope.launch{
                            val result  = userRepository.loginUser(email,password)
                            result.let {resourceRemote ->
                                when(resourceRemote){
                                    is ResourceRemote.Success ->{
                                        _registerSuccess.postValue(true)
                                        mensaje5.postValue ("Bienvenido")
                                    }
                                    is ResourceRemote.Error->{
                                        var msg = result.message
                                        when(msg){
                                            "The email address is already in use by another account."-> msg= "Ya existe una cuenta con ese correo"
                                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred."-> msg=" conectese a una red"
                                            "An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]"-> msg= "contrasena invalida"
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
}}
        }   }   }

//j
