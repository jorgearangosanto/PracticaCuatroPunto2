package com.santo.practicacuatro.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santo.practicacuatro.R
import com.santo.practicacuatro.databinding.ActivityLoginBinding
import com.santo.practicacuatro.ui.bottom.BottomNavigationActivity
import com.santo.practicacuatro.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginMainViewModel: LoginMainViewModel





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginMainViewModel = ViewModelProvider(this)[LoginMainViewModel::class.java]

        val view = loginBinding.root
        setContentView(view)
        /*val mensaje5observer = Observer<String>{mensaje5->
            loginBinding.IrButton3.setText(mensaje5.toString())

        }
        loginMainViewModel.mensaje5.observe(this,mensaje5observer)*/
        /*loginMainViewModel.mensaje5.observe(this){
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }*/


        loginBinding.registerButton.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        val mensaje5observer = Observer<String> {mensaje5 ->
            Toast.makeText(this, mensaje5, Toast.LENGTH_LONG).show()}
        loginMainViewModel.mensaje5.observe(this,mensaje5observer)

        /*val mensaje5observer = Observer<String>{mensaje5->
            loginBinding.textInputEditText.setText(mensaje5.toString())

        }
        loginMainViewModel.mensaje5.observe(this,mensaje5observer)*/

        loginMainViewModel.registerSuccess.observe(this){
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBinding.IrButton3.setOnClickListener {
            val email : String = loginBinding.textInputEditText.text.toString()
            val password : String = loginBinding.passordInput.text.toString()
            /*val intent = Intent(this, BottomNavigationActivity::class.java)
           startActivity(intent)*/
            loginMainViewModel.validateFields(email,password)

        }
    }
}