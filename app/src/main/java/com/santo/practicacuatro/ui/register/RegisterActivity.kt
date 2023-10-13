package com.santo.practicacuatro.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santo.practicacuatro.R
import com.santo.practicacuatro.databinding.ActivityLoginBinding
import com.santo.practicacuatro.databinding.ActivityRegisterBinding
import com.santo.practicacuatro.ui.bottom.BottomNavigationActivity
import com.santo.practicacuatro.ui.login.LoginMainViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerMainViewModel: RegisterMainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        registerMainViewModel = ViewModelProvider(this)[RegisterMainViewModel::class.java]

        val view = registerBinding.root
        setContentView(view)

        /*val mensaje5observer = Observer<String>{mensaje5->
            registerBinding.textInputRegisterPassword.setText(mensaje5.toString())

        }
        registerMainViewModel.mensaje5.observe(this,mensaje5observer)*/
        val mensaje5observer = Observer<String> {mensaje5 ->
            Toast.makeText(this, mensaje5, Toast.LENGTH_LONG).show()}
        registerMainViewModel.mensaje5.observe(this,mensaje5observer)

        registerMainViewModel.registerSuccess.observe(this){
            onBackPressedDispatcher.onBackPressed()
        }


        registerBinding.buttonRegisterIr.setOnClickListener {
            val email : String = registerBinding.textInputEmailRegister.text.toString()
            val contrasena : String =registerBinding.textInputRegisterPassword.text.toString()
            val repcontrasena : String =registerBinding.textInputReppasworwdRegister.text.toString()
            /*val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)*/

            registerMainViewModel.validateRegister(email,contrasena,repcontrasena)

        }


    }
}